package by.budanitskaya.l.quilixtest.presentation.ui.currencyinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.budanitskaya.l.quilixtest.data.network.models.RemoteCurrencyInfo
import by.budanitskaya.l.quilixtest.data.network.models.RemoteResponseData
import by.budanitskaya.l.quilixtest.data.network.responsewrapper.ResponseStatus
import by.budanitskaya.l.quilixtest.presentation.models.CurrencyPresentationModel
import by.budanitskaya.l.quilixtest.data.repository.CurrencyRepository
import by.budanitskaya.l.quilixtest.data.repository.PrefsCallback
import by.budanitskaya.l.quilixtest.data.repository.SettingsRepository
import by.budanitskaya.l.quilixtest.utils.getFormatDate
import by.budanitskaya.l.quilixtest.utils.makePresentationList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyInfoViewModel @Inject constructor(
    var currencyRepository: CurrencyRepository,
    val settingsRepository: SettingsRepository
) :
    ViewModel(), PrefsCallback {

    private val _currencyDataList = MutableLiveData<List<CurrencyPresentationModel>>()
    val currencyDataList: LiveData<List<CurrencyPresentationModel>> = _currencyDataList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isMenuVisible = MutableLiveData<Unit>()
    val isMenuVisible: LiveData<Unit> = _isMenuVisible

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    private lateinit var dates: Pair<String, String>
    private lateinit var currencyInitialList: List<CurrencyPresentationModel>

    init {
        fetchData()
        settingsRepository.prefsCallback = this
    }

    private fun fetchData() {
        _isLoading.value = true
        viewModelScope.launch {
            val todaysResponse = fetchThisDateData(getFormatDate(0L))
            val tomorrowsResponse = fetchThisDateData(getFormatDate(1L))

            if (tomorrowsResponse !is ResponseStatus.Success<RemoteResponseData>
                && todaysResponse is ResponseStatus.Success<RemoteResponseData>
            ) {
                // yesterdays flow
                val todaysCurrencies = todaysResponse.value.listRemoteCurrencyInfo
                val yesterdayResponse = fetchThisDateData(getFormatDate(-1L))
                if (yesterdayResponse is ResponseStatus.Success<RemoteResponseData>) {
                    val yesTerDaysCurrencies = yesterdayResponse.value.listRemoteCurrencyInfo
                    handleYesterDaysCase(yesTerDaysCurrencies, todaysCurrencies)
                }
            }

            if (todaysResponse !is ResponseStatus.Success<RemoteResponseData>
                && tomorrowsResponse !is ResponseStatus.Success<RemoteResponseData>
            ) {
                handleError()
            }
            if (tomorrowsResponse is ResponseStatus.Success<RemoteResponseData>
                && todaysResponse is ResponseStatus.Success<RemoteResponseData>
            ) {
                val nextDayList = tomorrowsResponse.value.listRemoteCurrencyInfo ?: emptyList()
                handleTommorowsCase(
                    nextDayList,
                    todaysResponse.value.listRemoteCurrencyInfo?: emptyList()
                )
            }
        }
    }

    private fun handleTommorowsCase(
        nextDayList: List<RemoteCurrencyInfo>,
        todaysCurrencies: List<RemoteCurrencyInfo>
    ) {
        _isLoading.value = false
        dates = Pair(
            getFormatDate(0L),
            getFormatDate(1L)
        )
        currencyInitialList = makePresentationList(nextDayList, todaysCurrencies)
        _currencyDataList.value =
            settingsRepository.applyPrefsToCurrencyList(currencyInitialList)
    }

    private fun handleError() {
        _isError.value = true
        _isMenuVisible.value = Unit
        _isLoading.value = false
    }

    private fun handleYesterDaysCase(
        yesTerDaysCurrencies: List<RemoteCurrencyInfo>?,
        todaysCurrencies: List<RemoteCurrencyInfo>?
    ) {
        _isLoading.value = false
        dates = Pair(
            getFormatDate(-1L),
            getFormatDate(0L)
        )
        currencyInitialList =
            makePresentationList(
                yesTerDaysCurrencies ?: emptyList(),
                todaysCurrencies ?: emptyList()
            )
        _currencyDataList.value =
            settingsRepository.applyPrefsToCurrencyList(currencyInitialList)
    }


    private suspend fun fetchThisDateData(date: String) = currencyRepository.fetchData(date)

    override fun onPrefsChanged() {
        _currencyDataList.value = settingsRepository.applyPrefsToCurrencyList(currencyInitialList)
    }

    fun getDates() = dates
}
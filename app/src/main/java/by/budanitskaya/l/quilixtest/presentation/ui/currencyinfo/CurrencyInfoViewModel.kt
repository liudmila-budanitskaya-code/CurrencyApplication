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
import by.budanitskaya.l.quilixtest.data.repository.SettingsRepositoryImpl
import by.budanitskaya.l.quilixtest.utils.getDateFromNow
import by.budanitskaya.l.quilixtest.utils.makePresentationModelList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyInfoViewModel @Inject constructor(
    private var currencyRepository: CurrencyRepository,
    val settingsRepository: SettingsRepository
) :
    ViewModel(), PrefsCallback {

    companion object {
        const val PLUS_ONE_DAY = 1L
        const val MINUS_ONE_DAY = -1L
        const val THIS_DAY = 0L
    }

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
        (settingsRepository as SettingsRepositoryImpl).prefsCallback = this
    }

    private fun fetchData() {
        _isLoading.value = true
        viewModelScope.launch {
            val todaysResponse = fetchThisDateData(getDateFromNow(THIS_DAY))
            if (todaysResponse !is ResponseStatus.Success<RemoteResponseData>) {
                handleError()
                return@launch
            }
            val thisDayList = todaysResponse.value.listRemoteCurrencyInfo ?: emptyList()

            val tomorrowsResponse = fetchThisDateData(getDateFromNow(PLUS_ONE_DAY))

            if (tomorrowsResponse is ResponseStatus.Success<RemoteResponseData>) {
                //tomorrows case
                val nextDayList = tomorrowsResponse.value.listRemoteCurrencyInfo ?: emptyList()
                handleTommorowsCase(
                    nextDayList,
                    thisDayList
                )
                return@launch
            }
            val yesterdayResponse = fetchThisDateData(getDateFromNow(MINUS_ONE_DAY))
            if (yesterdayResponse is ResponseStatus.Success<RemoteResponseData>) {
                // yesterdays flow
                val yesTerDaysCurrencies = yesterdayResponse.value.listRemoteCurrencyInfo
                handleYesterDaysCase(yesTerDaysCurrencies, thisDayList)
            } else {
                handleError()
            }
            return@launch
        }
    }

    private fun handleTommorowsCase(
        nextDayList: List<RemoteCurrencyInfo>,
        todaysCurrencies: List<RemoteCurrencyInfo>
    ) {
        _isLoading.value = false
        dates = Pair(
            getDateFromNow(THIS_DAY),
            getDateFromNow(PLUS_ONE_DAY)
        )
        currencyInitialList = makePresentationModelList(nextDayList, todaysCurrencies)
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
            getDateFromNow(MINUS_ONE_DAY),
            getDateFromNow(THIS_DAY)
        )
        currencyInitialList =
            makePresentationModelList(
                yesTerDaysCurrencies ?: emptyList(),
                todaysCurrencies ?: emptyList()
            )
        _currencyDataList.value =
            settingsRepository.applyPrefsToCurrencyList(currencyInitialList)
    }

    private suspend fun fetchThisDateData(date: String) = currencyRepository.fetchData(date)

    override fun onPrefsChanged() {
        _currencyDataList.value =
            settingsRepository.applyPrefsToCurrencyList(currencyInitialList)
    }

    fun getDates() = dates
}
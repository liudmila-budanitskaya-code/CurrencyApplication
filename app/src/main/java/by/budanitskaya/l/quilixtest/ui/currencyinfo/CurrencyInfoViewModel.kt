package by.budanitskaya.l.quilixtest.ui.currencyinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.budanitskaya.l.quilixtest.network.models.CurrencyInfo
import by.budanitskaya.l.quilixtest.network.models.ResponseData
import by.budanitskaya.l.quilixtest.network.safeapicall.ResponseStatus
import by.budanitskaya.l.quilixtest.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyInfoViewModel @Inject constructor(var currencyRepository: CurrencyRepository) :
    ViewModel() {

    private val _currencyDataList = MutableLiveData<List<CurrencyInfo>>()
    val currencyDataList : LiveData<List<CurrencyInfo>> = _currencyDataList

    private lateinit var data: ResponseStatus<ResponseData>

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            data = currencyRepository.fetchData("10/28/2021")
            when (data) {
                is ResponseStatus.Success -> {
                    _currencyDataList.value = (data as ResponseStatus.Success<ResponseData>).value.listCurrencyInfo
                }
                is ResponseStatus.Failure -> {
                }
            }
        }
    }

    fun retrieveCurrencyList() {
        viewModelScope.launch {
            data = currencyRepository.fetchData("10/28/2021")
            when (data) {
                is ResponseStatus.Success -> {
//                    _message.value =
//                        (data as ResponseStatus.Success<ResponseData>).value.listCurrencyInfo?.get(0)?.name
                }
                is ResponseStatus.Failure -> {
                }
            }
        }
    }
}
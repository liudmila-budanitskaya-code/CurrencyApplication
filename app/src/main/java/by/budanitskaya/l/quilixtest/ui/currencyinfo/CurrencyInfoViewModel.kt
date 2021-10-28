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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyInfoViewModel @Inject constructor(var currencyRepository: CurrencyRepository) :
    ViewModel() {

    private val _currencyDataList = MutableLiveData<List<CurrencyInfo>>()
    val currencyDataList: LiveData<List<CurrencyInfo>> = _currencyDataList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    private lateinit var data: ResponseStatus<ResponseData>

    init {
        fetchData()
    }

    private fun fetchData() {
        _isLoading.value = true
        viewModelScope.launch {
            data = currencyRepository.fetchData("10/28/2021")
            delay(3000)
            when (data) {
                is ResponseStatus.Success -> {
                    _currencyDataList.value =
                        (data as ResponseStatus.Success<ResponseData>).value.listCurrencyInfo
                    _isLoading.value = false
                }
                is ResponseStatus.Failure -> {
                    _isError.value = true
                    _isLoading.value = false
                }
            }
        }
    }
}
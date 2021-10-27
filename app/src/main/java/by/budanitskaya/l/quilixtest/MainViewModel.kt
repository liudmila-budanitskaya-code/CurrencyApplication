package by.budanitskaya.l.quilixtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.budanitskaya.l.quilixtest.datasource.CurrencyDataSource
import by.budanitskaya.l.quilixtest.network.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var currencyDataSource: CurrencyDataSource

    private var _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private lateinit var data: ResponseStatus<ResponseData>

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            delay(3000)
            data = currencyDataSource.fetchData("10/28/2021")
            when (data) {
                is ResponseStatus.Success -> {
                    _message.value =
                        (data as ResponseStatus.Success<ResponseData>).value.listCurrencyInfo?.get(0)?.name
                }
                is ResponseStatus.Failure -> {
                }
            }
        }
    }
}
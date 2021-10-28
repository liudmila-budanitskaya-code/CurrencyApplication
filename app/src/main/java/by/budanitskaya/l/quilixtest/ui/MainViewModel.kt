package by.budanitskaya.l.quilixtest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.budanitskaya.l.quilixtest.datasource.CurrencyDataSource
import by.budanitskaya.l.quilixtest.network.safeapicall.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import by.budanitskaya.l.quilixtest.network.models.ResponseData
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var currencyDataSource: CurrencyDataSource) : ViewModel() {

    private var _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private lateinit var data: ResponseStatus<ResponseData>

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
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
package by.budanitskaya.l.quilixtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _data = MutableLiveData<ResponseData>()
    val data: LiveData<ResponseData> = _data

    init {
        fetchData()
    }

    private fun fetchData() {
        val retrofit = createApiService<ApiService>(BuildConfig.SERVER_URL)
        viewModelScope.launch {
            delay(3000)
            _data.value = retrofit.getFeed("10/28/2021")
        }
    }
}
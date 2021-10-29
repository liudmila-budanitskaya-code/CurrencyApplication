package by.budanitskaya.l.quilixtest.data.datasource

import by.budanitskaya.l.quilixtest.data.network.apiservice.ApiService
import by.budanitskaya.l.quilixtest.data.network.models.ResponseData
import by.budanitskaya.l.quilixtest.data.network.safeapicall.ResponseStatus
import by.budanitskaya.l.quilixtest.data.network.safeapicall.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyDataSource @Inject constructor(val apiService: ApiService) {

    @Inject
    lateinit var safeApiCall: SafeApiCall

    suspend fun fetchData(onDate: String): ResponseStatus<ResponseData> =
        safeApiCall.safeApiCall { apiService.getFeed(onDate) }
}
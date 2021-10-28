package by.budanitskaya.l.quilixtest.datasource

import by.budanitskaya.l.quilixtest.network.apiservice.ApiService
import by.budanitskaya.l.quilixtest.network.models.ResponseData
import by.budanitskaya.l.quilixtest.network.safeapicall.ResponseStatus
import by.budanitskaya.l.quilixtest.network.safeapicall.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyDataSource @Inject constructor(val apiService: ApiService) {

    @Inject
    lateinit var safeApiCall: SafeApiCall

    suspend fun fetchData(onDate: String): ResponseStatus<ResponseData> =
        safeApiCall.safeApiCall { apiService.getFeed(onDate) }
}
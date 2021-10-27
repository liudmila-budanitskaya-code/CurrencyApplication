package by.budanitskaya.l.quilixtest.datasource

import by.budanitskaya.l.quilixtest.ApiService
import by.budanitskaya.l.quilixtest.ResponseData
import by.budanitskaya.l.quilixtest.network.ResponseStatus
import by.budanitskaya.l.quilixtest.network.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyDataSource @Inject constructor(val apiService: ApiService) {

    @Inject
    lateinit var safeApiCall: SafeApiCall

    suspend fun fetchData(onDate: String): ResponseStatus<ResponseData> =
        safeApiCall.safeApiCall { apiService.getFeed(onDate) }
}
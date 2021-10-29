package by.budanitskaya.l.quilixtest.data.datasource

import by.budanitskaya.l.quilixtest.data.network.apiservice.ApiService
import by.budanitskaya.l.quilixtest.data.network.models.RemoteResponseData
import by.budanitskaya.l.quilixtest.data.network.responsewrapper.ResponseStatus
import by.budanitskaya.l.quilixtest.data.network.responsewrapper.SafeApiCall
import javax.inject.Inject

class CurrencyDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    CurrencyDataSource {

    @Inject
    lateinit var safeApiCall: SafeApiCall

    override suspend fun fetchData(onDate: String): ResponseStatus<RemoteResponseData> =
        safeApiCall.safeApiCall { apiService.getCurrencyInfo(onDate) }
}
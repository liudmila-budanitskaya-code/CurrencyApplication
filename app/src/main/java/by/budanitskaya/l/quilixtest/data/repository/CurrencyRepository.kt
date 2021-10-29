package by.budanitskaya.l.quilixtest.data.repository

import by.budanitskaya.l.quilixtest.data.datasource.CurrencyDataSource
import by.budanitskaya.l.quilixtest.data.network.models.RemoteResponseData
import by.budanitskaya.l.quilixtest.data.network.responsewrapper.ResponseStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepository @Inject constructor(private val currencyDataSource: CurrencyDataSource) {

    suspend fun fetchData(onDate: String): ResponseStatus<RemoteResponseData> =
        currencyDataSource.fetchData(onDate)
}
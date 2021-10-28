package by.budanitskaya.l.quilixtest.repository

import by.budanitskaya.l.quilixtest.datasource.CurrencyDataSource
import by.budanitskaya.l.quilixtest.network.models.ResponseData
import by.budanitskaya.l.quilixtest.network.safeapicall.ResponseStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepository @Inject constructor(val currencyDataSource: CurrencyDataSource) {

    suspend fun fetchData(onDate: String): ResponseStatus<ResponseData> =
        currencyDataSource.fetchData(onDate)
}
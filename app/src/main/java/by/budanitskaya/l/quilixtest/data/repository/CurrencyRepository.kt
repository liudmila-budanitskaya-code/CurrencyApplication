package by.budanitskaya.l.quilixtest.data.repository

import by.budanitskaya.l.quilixtest.data.datasource.CurrencyDataSource
import by.budanitskaya.l.quilixtest.data.network.models.ResponseData
import by.budanitskaya.l.quilixtest.data.network.safeapicall.ResponseStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRepository @Inject constructor(val currencyDataSource: CurrencyDataSource) {

    suspend fun fetchData(onDate: String): ResponseStatus<ResponseData> =
        currencyDataSource.fetchData(onDate)
}
package by.budanitskaya.l.quilixtest.data.datasource

import by.budanitskaya.l.quilixtest.data.network.models.RemoteResponseData
import by.budanitskaya.l.quilixtest.data.network.responsewrapper.ResponseStatus

interface CurrencyDataSource {
    suspend fun fetchData(onDate: String): ResponseStatus<RemoteResponseData>
}
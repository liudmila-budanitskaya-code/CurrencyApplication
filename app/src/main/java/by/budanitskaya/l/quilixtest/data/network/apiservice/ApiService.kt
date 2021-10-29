package by.budanitskaya.l.quilixtest.data.network.apiservice

import by.budanitskaya.l.quilixtest.data.network.models.RemoteResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("Services/XmlExRates.aspx")
    suspend fun getCurrencyInfo(@Query("ondate") onDate: String): RemoteResponseData
}
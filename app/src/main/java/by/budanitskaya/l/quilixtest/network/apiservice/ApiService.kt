package by.budanitskaya.l.quilixtest.network.apiservice


import by.budanitskaya.l.quilixtest.network.models.ResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("Services/XmlExRates.aspx")
    suspend fun getFeed(@Query("ondate") onDate: String): ResponseData
}
package by.budanitskaya.l.quilixtest


import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("Services/XmlExRates.aspx")
    suspend fun getFeed(@Query("ondate") onDate: String): ResponseData
}


@Root(name = "DailyExRates", strict = false)
data class ResponseData @JvmOverloads constructor(
    @field:ElementList(name = "Currency", inline = true, required = false)
    @param:ElementList(name = "Currency", inline = true, required = false)
    var listCurrencyInfo: List<CurrencyInfo>? =
        null
)

@Root(name = "Currency", strict = false)
data class CurrencyInfo @JvmOverloads constructor(
    @field:Element(name = "NumCode")
    @param:Element(name = "NumCode")
    var numCode: Int? = null,

    @field:Element(name = "CharCode")
    @param:Element(name = "CharCode")
    var charCode: String? = null,

    @field:Element(name = "Scale")
    @param:Element(name = "Scale")
    var scale: Int? = null,

    @field:Element(name = "Name")
    @param:Element(name = "Name")
    var name: String? = null,

    @field:Element(name = "Rate")
    @param:Element(name = "Rate")
    var rate: Float? = null,
)
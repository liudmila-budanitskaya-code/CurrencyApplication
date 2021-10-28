package by.budanitskaya.l.quilixtest.network.models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "DailyExRates", strict = false)
data class ResponseData @JvmOverloads constructor(
    @field:ElementList(name = "Currency", inline = true, required = false)
    @param:ElementList(name = "Currency", inline = true, required = false)
    var listCurrencyInfo: List<CurrencyInfo>? =
        null
)
package by.budanitskaya.l.quilixtest.data.network.models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "DailyExRates", strict = false)
data class RemoteResponseData @JvmOverloads constructor(
    @field:ElementList(name = "Currency", inline = true, required = false)
    @param:ElementList(name = "Currency", inline = true, required = false)
    var listRemoteCurrencyInfo: List<RemoteCurrencyInfo>?
)
package by.budanitskaya.l.quilixtest.network.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

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
package by.budanitskaya.l.quilixtest.data.network.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Currency", strict = false)
data class RemoteCurrencyInfo @JvmOverloads constructor(
    @field:Element(name = "NumCode")
    @param:Element(name = "NumCode")
    var numCode: Int?,

    @field:Element(name = "CharCode")
    @param:Element(name = "CharCode")
    var charCode: String?,

    @field:Element(name = "Scale")
    @param:Element(name = "Scale")
    var scale: Int?,

    @field:Element(name = "Name")
    @param:Element(name = "Name")
    var name: String?,

    @field:Element(name = "Rate")
    @param:Element(name = "Rate")
    var rate: Float?
)
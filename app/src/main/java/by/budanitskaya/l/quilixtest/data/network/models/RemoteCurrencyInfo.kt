package by.budanitskaya.l.quilixtest.data.network.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Currency", strict = false)
data class RemoteCurrencyInfo @JvmOverloads constructor(
    @field:Element(name = "NumCode")
    @param:Element(name = "NumCode")
    val numCode: Int?,

    @field:Element(name = "CharCode")
    @param:Element(name = "CharCode")
    val charCode: String?,

    @field:Element(name = "Scale")
    @param:Element(name = "Scale")
    val scale: Int?,

    @field:Element(name = "Name")
    @param:Element(name = "Name")
    val name: String?,

    @field:Element(name = "Rate")
    @param:Element(name = "Rate")
    val rate: Float?
)
package by.budanitskaya.l.quilixtest.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getDateFromNow(number: Long): String {
    val date = LocalDateTime.now().plusDays(number)
    val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
    return date.format(formatter)
}



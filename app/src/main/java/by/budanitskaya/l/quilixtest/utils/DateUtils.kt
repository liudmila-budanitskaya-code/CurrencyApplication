package by.budanitskaya.l.quilixtest.utils

import android.util.Log
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getCurrentDatePlusDays(number: Long) = LocalDateTime.now().plusDays(number)

fun getCurrentFormatDate(date: LocalDateTime): String {
    var formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
    Log.d("getCurrentFormatDate", "getCurrentFormatDate: ${date.format(formatter)}")
    return date.format(formatter)
}



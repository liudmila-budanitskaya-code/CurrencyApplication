package by.budanitskaya.l.quilixtest.network.safeapicall

import okhttp3.ResponseBody

sealed class ResponseStatus<out T> {
    data class Success<out T>(val value: T) : ResponseStatus<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : ResponseStatus<Nothing>()
}
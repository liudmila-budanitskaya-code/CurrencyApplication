package by.budanitskaya.l.quilixtest.data.network.responsewrapper

import okhttp3.ResponseBody

sealed class ResponseStatus<out T> {
    data class Success<out T>(val value: T) : ResponseStatus<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : ResponseStatus<Nothing>()
}
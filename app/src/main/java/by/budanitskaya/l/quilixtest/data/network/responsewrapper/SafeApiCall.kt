package by.budanitskaya.l.quilixtest.data.network.responsewrapper

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): ResponseStatus<T>

    fun handleError(throwable: Throwable): ResponseStatus.Failure
}
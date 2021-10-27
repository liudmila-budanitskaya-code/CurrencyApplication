package by.budanitskaya.l.quilixtest.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): ResponseStatus<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResponseStatus.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        ResponseStatus.Failure(
                            false,
                            throwable.code(),
                            throwable.response()?.errorBody()
                        )
                    }
                    else -> {
                        ResponseStatus.Failure(true, null, null)
                    }
                }
            }
        }
    }
}
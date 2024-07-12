package org.lotka.xenonx.domain.util

import org.lotka.xenonx.domain.ErrorMessage2


//sealed class ResultState<T> {
//    data class Success<T>(val data: T?) : ResultState<T>()
//    data class Error<T>(val error: ErrorMessage2) : ResultState<T>()
//    data class Loading<T>(val loading: Boolean) : ResultState<T>()
//}


sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(null)
}


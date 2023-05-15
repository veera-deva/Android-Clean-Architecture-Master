package com.demo.domain.model

sealed interface NetworkResult<T : Any> {
    class Success<T : Any>(val data: T) : NetworkResult<T>
    class Failure<T : Any>(val e: Throwable) : NetworkResult<T>
    class Error<T : Any>(val code: Int, val message: String) : NetworkResult<T>
}

package com.example.waitingroom.utils

sealed class Resource<T>(val value: T? = null, val code: Int? = null) {
    class Success<T>(value: T) : Resource<T>(value)
    class Error<T>(code: Int, value: T? = null) : Resource<T>(value, code)
    class Loading<T>(value: T? = null) : Resource<T>(value)
}
package com.melisa.loodos.data.repository

sealed class Output<out T: Any> {
    data class Success<out T : Any>(val data: T) : Output<T>()
    data class Error(val exception: Throwable) : Output<Nothing>()
}
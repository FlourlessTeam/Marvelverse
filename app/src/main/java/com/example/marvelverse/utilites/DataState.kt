package com.example.marvelverse.utilites

sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    object NoResult : DataState<Nothing>()
    data class Error(val e: Throwable) : DataState<Nothing>()
    data class Success<T>(val data: List<T>) : DataState<T>()

    fun toData():List<T>? = if(this is Success) data else null
}
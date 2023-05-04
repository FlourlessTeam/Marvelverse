package com.example.marvelverse

sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    data class Error(val e: Throwable) : DataState<Nothing>()
    data class Success<T>(val data: T) : DataState<T>()
}
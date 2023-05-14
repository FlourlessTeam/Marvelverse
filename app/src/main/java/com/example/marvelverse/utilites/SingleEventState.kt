package com.example.marvelverse.utilites

class SingleEventState<out T>(private val data: T) {
    private var isHandled = false
    fun getUnHandledData(): T? {
        return if (isHandled) {
            null
        } else {
            isHandled = true
            data
        }
    }
}
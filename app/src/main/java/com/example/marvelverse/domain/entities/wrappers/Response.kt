package com.example.marvelverse.domain.entities.wrappers

data class Response<T>(
    val data: DataWrapper<T>,
)
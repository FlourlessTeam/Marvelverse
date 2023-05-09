package com.example.marvelverse.domain.entities.wrappers

import java.io.Serializable

data class Response<T>(
    val data: DataWrapper<T>,
):Serializable
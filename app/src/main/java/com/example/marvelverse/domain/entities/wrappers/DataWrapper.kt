package com.example.marvelverse.domain.entities.wrappers

data class DataWrapper<T>(
    val results: List<T>,
    val limit: Int,
    val offset: Int,
    val count: Int,
    val total: Int
)

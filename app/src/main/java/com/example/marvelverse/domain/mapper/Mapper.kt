package com.example.marvelverse.domain.mapper

interface Mapper<I,O> {
    fun map(input: I): O
}
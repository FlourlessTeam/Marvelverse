package com.example.marvelverse.domain.entities

data class SearchKeyword(val keyword: String){
    val timestamp = System.currentTimeMillis()
}
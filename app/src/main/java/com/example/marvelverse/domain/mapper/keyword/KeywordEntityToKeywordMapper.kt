package com.example.marvelverse.domain.mapper.keyword

import com.example.marvelverse.data.dataSources.local.entities.search.SearchKeywordEntity
import com.example.marvelverse.domain.entities.SearchKeyword
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class KeywordEntityToKeywordMapper  @Inject constructor():
    Mapper<SearchKeywordEntity, SearchKeyword> {
    override fun map(input: SearchKeywordEntity): SearchKeyword {
        return SearchKeyword(input.keyword)
    }
}
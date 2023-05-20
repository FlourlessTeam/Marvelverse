package com.example.marvelverse.domain.mapper.keyword

import com.example.marvelverse.data.dataSources.local.entities.search.SearchKeywordEntity
import com.example.marvelverse.domain.entities.SearchKeyword
import com.example.marvelverse.domain.mapper.Mapper
import javax.inject.Inject

class KeywordToKeywordEntityMapper  @Inject constructor():
    Mapper<SearchKeyword, SearchKeywordEntity> {
    override fun map(input: SearchKeyword): SearchKeywordEntity {
        return SearchKeywordEntity(null ,input.keyword,input.timestamp)
    }
}

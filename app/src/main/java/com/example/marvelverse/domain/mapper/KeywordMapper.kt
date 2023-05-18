package com.example.marvelverse.domain.mapper

import com.example.marvelverse.data.dataSources.local.entities.search.SearchKeywordEntity
import com.example.marvelverse.domain.entities.SearchKeyword

class KeywordToKeywordEntityMapper:Mapper<SearchKeyword,SearchKeywordEntity> {
	override fun map(input: SearchKeyword): SearchKeywordEntity {
		return SearchKeywordEntity(input.keyword,input.timestamp)
	}
}
class KeywordEntityToKeywordMapper:Mapper<SearchKeywordEntity,SearchKeyword> {
	override fun map(input: SearchKeywordEntity): SearchKeyword {
		return SearchKeyword(input.keyword)
	}
}
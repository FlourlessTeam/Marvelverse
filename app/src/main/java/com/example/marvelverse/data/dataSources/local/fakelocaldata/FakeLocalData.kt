package com.example.marvelverse.data.dataSources.local.fakelocaldata

import com.example.marvelverse.domain.entities.About

interface FakeLocalData {
	fun getAboutItems(): List<About>
}
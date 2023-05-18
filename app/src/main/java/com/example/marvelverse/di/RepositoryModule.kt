package com.example.marvelverse.di

import com.example.marvelverse.data.dataSources.local.FakeLocalData
import com.example.marvelverse.data.dataSources.local.dao.HomeDao
import com.example.marvelverse.data.dataSources.local.dao.SearchDao
import com.example.marvelverse.data.dataSources.remote.MarvelApiServices
import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.domain.mapper.MappersContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMarvelRepository(
        marvelApiServices: MarvelApiServices,
        dataMapper: MappersContainer,
        fakeLocalData: FakeLocalData,
        homeDao: HomeDao,
        searchDao: SearchDao,
    ): MarvelRepository {
        return MarvelRepository(
            marvelApiServices,
            dataMapper,
            fakeLocalData,
            homeDao,
            searchDao
        )
    }
}
package com.example.marvelverse.di

import com.example.marvelverse.data.repositories.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMarvelRepository(): MarvelRepository {
        return MarvelRepository()
    }
}
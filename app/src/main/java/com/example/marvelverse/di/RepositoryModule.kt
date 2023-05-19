package com.example.marvelverse.di

import com.example.marvelverse.data.repositories.MarvelRepository
import com.example.marvelverse.data.repositories.MarvelRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideMarvelRepository(
        marvelRepositoryImp: MarvelRepositoryImp
    ): MarvelRepository

    }

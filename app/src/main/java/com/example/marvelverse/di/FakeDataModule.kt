package com.example.marvelverse.di

import com.example.marvelverse.data.dataSources.local.fakelocaldata.FakeLocalData
import com.example.marvelverse.data.dataSources.local.fakelocaldata.FakeLocalDataImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FakeDataModule {
	@Binds
	abstract fun provideFakeLocalData(
		fakeLocalDataImp: FakeLocalDataImp
	): FakeLocalData
}
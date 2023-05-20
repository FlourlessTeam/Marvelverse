package com.example.marvelverse.di

import com.example.marvelverse.BuildConfig
import com.example.marvelverse.data.dataSources.remote.AuthInterceptor
import com.example.marvelverse.data.dataSources.remote.MarvelApiServices
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	fun provideMarvelService(okHttpClient: OkHttpClient, moshi: Moshi): MarvelApiServices {
		val retrofit = Retrofit.Builder()
			.baseUrl(BuildConfig.base_url)
			.client(okHttpClient)
			.addConverterFactory(MoshiConverterFactory.create(moshi))
			.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
			.build()
		return retrofit.create(MarvelApiServices::class.java)
	}

	@Provides
	fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
		return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
	}

	@Provides
	fun provideMoshi(): Moshi {
		return Moshi.Builder().build()
	}

	@Provides
	fun provideAuthInterceptor(): AuthInterceptor {
		return AuthInterceptor()
	}

}
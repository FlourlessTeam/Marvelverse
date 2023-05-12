package com.example.marvelverse.data.dataSources.remote

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    //    private val gson = GsonBuilder()
//        .registerTypeAdapter(Response::class.java,ResponseDeserializer<List<Comic>>())
//        .create()

    val moshi = Moshi.Builder().build()
    private val retrofit: Retrofit by lazy {
        val client = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }


    val marvelApiServices: MarvelApiServices by lazy {
        retrofit.create(MarvelApiServices::class.java)
    }
}

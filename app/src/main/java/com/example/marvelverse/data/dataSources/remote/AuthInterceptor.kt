package com.example.marvelverse.data.dataSources.remote

import com.example.marvelverse.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest
import java.util.Locale
import javax.xml.bind.DatatypeConverter

class AuthInterceptor : Interceptor {
    companion object{
        private const val publicKey = BuildConfig.public_key
        private const val privateKey = BuildConfig.private_key
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val timeStamp = System.currentTimeMillis().toString()
        val hash = generateMDFive(timeStamp)
        val original = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("hash", hash)
            .addQueryParameter("ts", timeStamp)
            .build()
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun generateMDFive(timeStamp: String): String {
        val message = timeStamp + privateKey + publicKey
        val md = MessageDigest.getInstance("MD5")
        val hash = md.digest(message.toByteArray())
        return DatatypeConverter.printHexBinary(hash).lowercase(Locale.getDefault())
    }
}



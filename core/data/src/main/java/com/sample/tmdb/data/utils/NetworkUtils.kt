package com.sample.tmdb.data.utils

import com.sample.tmdb.data.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun createNetworkClient(baseUrl: String) =
    retrofitClient(baseUrl, httpClient())

private fun httpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }
    clientBuilder.addInterceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
            .build()

        val request = original.newBuilder().url(url).build()
        chain.proceed(request)
    }
    clientBuilder.readTimeout(120, TimeUnit.SECONDS)
    clientBuilder.writeTimeout(120, TimeUnit.SECONDS)
    return clientBuilder.build()
}

private val moshiBuilder = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private fun retrofitClient(
    baseUrl: String,
    httpClient: OkHttpClient
): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshiBuilder))
        .build()
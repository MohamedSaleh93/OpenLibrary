package com.mohamed.opendocumentlibrary.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mohamed.opendocumentlibrary.BuildConfig
import com.mohamed.opendocumentlibrary.network.DocumentsApiService
import com.mohamed.opendocumentlibrary.utils.Constants
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
	single { provideOkHttpClient() }
	single { provideRetrofit(get(), Constants.BASE_URL) }
	single { provideDocumentsApiServices(get()) }
}

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
	val loggingInterceptor = HttpLoggingInterceptor()
	loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
	OkHttpClient.Builder()
		.addInterceptor(loggingInterceptor)
		.build()
} else {
	OkHttpClient.Builder()
		.build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit {
	val contentType = "application/json".toMediaType()
	return Retrofit.Builder()
		.addConverterFactory(Json{ignoreUnknownKeys = true}.asConverterFactory(contentType))
		.baseUrl(BASE_URL)
		.client(okHttpClient)
		.build()
}

private fun provideDocumentsApiServices(retrofit: Retrofit): DocumentsApiService = retrofit.create(DocumentsApiService::class.java)

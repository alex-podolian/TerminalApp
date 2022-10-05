package com.zeller.network.di

import com.zeller.data.repository.RemoteSource
import com.zeller.network.NetworkSource
import com.zeller.network.services.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://zeller.someservice.staging/"

@Module
abstract class NetworkModule {

    @Singleton
    @Binds
    abstract fun provideNetworkSource(storage: NetworkSource): RemoteSource

    companion object {

        @Provides
        fun provideRequestInterceptor() : Interceptor {
            return Interceptor { chain ->
                val originalRequest = chain.request()
                val originalHttpUrl: HttpUrl = originalRequest.url
                val url = originalHttpUrl.newBuilder().build()
                val requestBuilder: Request.Builder = originalRequest.newBuilder().url(url)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
        }

        @Singleton
        @Provides
        fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(null)
                .build()
        }

        @Singleton
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

        @Singleton
        @Provides
        fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
    }
}
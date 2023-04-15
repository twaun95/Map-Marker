package com.twaun95.data.di

import com.twaun95.data.BuildConfig
import com.twaun95.data.service.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {
    private const val BASE_URL = BuildConfig.BASE_URL
    private const val AUTH_HEADER = "KakaoAK ${BuildConfig.API_KEY}"
    private const val TIME_OUT_COUNT = 30L

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor { chain ->
                chain.proceed(
                    chain.request()
                        .newBuilder()
                        .header("Authorization", AUTH_HEADER)
                        .build()
                ).also { response ->
                    Timber.d("Response Code: ${response.code}")
                    Timber.d("Response IsSuccessFul: ${response.isSuccessful}")
                    Timber.d("Response Body: ${response.peekBody(4096).string()}")
                }
            }
            .connectTimeout(TIME_OUT_COUNT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideSearchService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }
}
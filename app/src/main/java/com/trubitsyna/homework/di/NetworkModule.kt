package com.trubitsyna.homework.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.trubitsyna.homework.data.PrefsStorage
import com.trubitsyna.homework.data.remote.NanoPostApiService
import com.trubitsyna.homework.data.repository.ProfileRepository
import com.trubitsyna.homework.data.repository.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Converter
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://nanopost.evolitist.com/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @Qualifier annotation class AuthClient

    @Provides
    @Singleton
//    @AuthClient
    fun provideRetrofit(
        httpClient: OkHttpClient,
        json: Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
           // .client(httpClient)
            .addConverterFactory(
                json
            )
            .build()
    }

    @Provides
    fun provideJson(): Factory {
        return Json{ignoreUnknownKeys = true
        }.asConverterFactory("application/json".toMediaType())
    }

//    @Provides
//    @Singleton
//    fun provideAuthRetrofit(
//        json: Factory
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(
//                json
//            )
//            .build()
//    }

    @Provides
    @Singleton
    fun provideNanoPostApiService(
        retrofit: Retrofit
    ): NanoPostApiService {
        return retrofit.create()
    }

//    @Provides
//    @Singleton
//    fun provideNanopostAuthApiService(@AuthClient retrofit: Retrofit) {
//        return retrofit.create()
//    }
    @Provides
    @Singleton
    fun provideAuthInterceptor(
        prefsStorage: PrefsStorage,
    ): Interceptor {
        return Interceptor {chain ->
            val request = chain.request().newBuilder()
                .addHeader(
                    name = "Authorization",
                    "Bearer ${prefsStorage.token}"
                )
            chain.proceed(request.build())
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        authInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .build()
    }

}
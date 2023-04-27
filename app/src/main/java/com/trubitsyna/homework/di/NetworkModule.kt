package com.trubitsyna.homework.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.trubitsyna.homework.data.remote.NanoPostApiService
import com.trubitsyna.homework.data.repository.ProfileRepository
import com.trubitsyna.homework.data.repository.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://nanopost.evolitist.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(
                Json.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideNanoPostApiService(
        retrofit: Retrofit
    ): NanoPostApiService {
        return retrofit.create()
    }

}
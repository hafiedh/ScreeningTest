package com.hafidh.screeningtest.di

import com.hafidh.screeningtest.data.api.ApiService
import com.hafidh.screeningtest.data.repo.RepoImpl
import com.hafidh.screeningtest.data.repo.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): ApiService {
        val baseUrl = "http://www.mocky.io/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
    
    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository {
        return RepoImpl(apiService)
    }
}
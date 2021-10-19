package com.suonk.rickmortyapp.di

import com.suonk.rickmortyapp.api.RickAndMortyApiService
import com.suonk.rickmortyapp.api.RickAndMortyApiService.Companion.BASE_URL
import com.suonk.rickmortyapp.repositories.DefaultRepository
import com.suonk.rickmortyapp.repositories.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): RickAndMortyApiService =
        retrofit.create(RickAndMortyApiService::class.java)

    @Provides
    fun provideDefaultRepository(api: RickAndMortyApiService): DefaultRepository =
        MainRepository(api)
}
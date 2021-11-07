package com.suonk.rickmortyapp.api

import com.suonk.rickmortyapp.models.data.CharactersApiResponse
import com.suonk.rickmortyapp.models.data.EpisodesApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page")
        page: String?
    ): Response<CharactersApiResponse>

    @GET("location")
    suspend fun getAllLocations(
        @Query("page")
        page: String?
    ): Response<CharactersApiResponse>

    @GET("episode")
    suspend fun getAllEpisodes(
        @Query("page")
        page: String?
    ): Response<EpisodesApiResponse>
}
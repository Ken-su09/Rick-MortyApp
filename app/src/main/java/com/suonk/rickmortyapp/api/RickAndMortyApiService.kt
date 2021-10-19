package com.suonk.rickmortyapp.api

import com.suonk.rickmortyapp.models.data.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api"
    }

    @GET("/character/")
    suspend fun getAllCharacters(
        id: Int?,
        @Query("page")
        page: String?
    ): Response<ApiResponse>

    @GET("/location/")
    suspend fun getAllLocations(
        id: Int?,
        @Query("page")
        page: String?
    ): Response<ApiResponse>

    @GET("/episode/")
    suspend fun getAllEpisodes(
        id: Int?,
        @Query("page")
        page: String?
    ): Response<ApiResponse>
}
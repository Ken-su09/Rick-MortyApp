package com.suonk.rickmortyapp.repositories

import com.suonk.rickmortyapp.models.data.ApiResponse
import retrofit2.Response

interface DefaultRepository {

    suspend fun getAllCharacters(id: Int?, page: String?): Response<ApiResponse>
    suspend fun getAllLocations(id: Int?, page: String?): Response<ApiResponse>
    suspend fun getAllEpisodes(id: Int?, page: String?): Response<ApiResponse>
}
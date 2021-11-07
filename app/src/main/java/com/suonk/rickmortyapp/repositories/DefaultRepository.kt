package com.suonk.rickmortyapp.repositories

import com.suonk.rickmortyapp.models.data.CharactersApiResponse
import com.suonk.rickmortyapp.models.data.EpisodesApiResponse
import retrofit2.Response

interface DefaultRepository {

    suspend fun getAllCharacters(page: String?): Response<CharactersApiResponse>
    suspend fun getAllLocations(page: String?): Response<CharactersApiResponse>
    suspend fun getAllEpisodes(page: String?): Response<EpisodesApiResponse>
}
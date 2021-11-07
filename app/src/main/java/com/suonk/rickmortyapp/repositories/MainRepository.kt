package com.suonk.rickmortyapp.repositories

import com.suonk.rickmortyapp.api.RickAndMortyApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: RickAndMortyApiService) :
    DefaultRepository {

    override suspend fun getAllCharacters(page: String?) = api.getAllCharacters(page)

    override suspend fun getAllLocations(page: String?) = api.getAllLocations(page)

    override suspend fun getAllEpisodes(page: String?) = api.getAllEpisodes(page)
}
package com.suonk.rickmortyapp.repositories

import com.suonk.rickmortyapp.api.RickAndMortyApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: RickAndMortyApiService) :
    DefaultRepository {

    override suspend fun getAllCharacters(id: Int?, page: String?) = api.getAllCharacters(id, page)

    override suspend fun getAllLocations(id: Int?, page: String?) = api.getAllLocations(id, page)

    override suspend fun getAllEpisodes(id: Int?, page: String?) = api.getAllEpisodes(id, page)
}
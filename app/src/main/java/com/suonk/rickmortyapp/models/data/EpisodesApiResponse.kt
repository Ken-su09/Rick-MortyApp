package com.suonk.rickmortyapp.models.data

data class EpisodesApiResponse(
    val info: Info,
    val results: List<ResultX> = listOf()
)
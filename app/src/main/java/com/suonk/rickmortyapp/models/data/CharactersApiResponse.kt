package com.suonk.rickmortyapp.models.data

data class CharactersApiResponse(
    val info: Info,
    val results: MutableList<Result>
)
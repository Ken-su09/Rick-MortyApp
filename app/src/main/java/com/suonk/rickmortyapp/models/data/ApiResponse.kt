package com.suonk.rickmortyapp.models.data

data class ApiResponse(
    val info: Info,
    val results: List<Result>
)
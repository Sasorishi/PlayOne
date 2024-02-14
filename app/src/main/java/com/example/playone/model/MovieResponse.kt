package com.example.playone.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int
    // Vous pouvez inclure d'autres champs de pagination ou de métadonnées si nécessaire
)

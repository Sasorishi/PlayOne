package com.example.playone.model

data class Genre(
    val id: Int,
    val name: String
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val popularity: Float,
    val genres: List<Genre> // Utilisez cette clé pour construire l'URL de l'image
)

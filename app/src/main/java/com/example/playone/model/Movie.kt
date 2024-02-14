package com.example.playone.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String // Utilisez cette clé pour construire l'URL de l'image
)

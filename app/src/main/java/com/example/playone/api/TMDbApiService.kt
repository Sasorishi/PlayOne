package com.example.playone.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.playone.model.MovieResponse
import com.example.playone.model.Movie

interface TMDbApiService {
    // Exemple d'appel pour obtenir les films populaires
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MovieResponse>

    // Exemple d'appel pour obtenir les détails d'un film spécifique
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String): Response<Movie>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}


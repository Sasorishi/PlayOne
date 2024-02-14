// MoviesRepository.kt
package com.example.playone.repository

import com.example.playone.api.TMDbApiService
import com.example.playone.model.MovieResponse

class MoviesRepository(private val apiService: TMDbApiService) {

    suspend fun getPopularMovies(apiKey: String): MovieResponse? {
        return try {
            val response = apiService.getPopularMovies(apiKey)
            if (response.isSuccessful) {
                response.body()
            } else null
        } catch (e: Exception) {
            null
        }
    }
}

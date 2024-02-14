package com.example.playone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.playone.model.Movie
import com.example.playone.repository.MoviesRepository

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    fun fetchPopularMovies(apiKey: String) {
        viewModelScope.launch {
            val moviesResponse = repository.getPopularMovies(apiKey)
            _movies.value = moviesResponse?.results ?: emptyList()
        }
    }
}

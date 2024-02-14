package com.example.playone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playone.BuildConfig
import com.example.playone.api.TMDbApiService
import com.example.playone.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesViewModel : ViewModel() {
    private val tmDbApiService: TMDbApiService by lazy {
        Retrofit.Builder()
            .baseUrl(TMDbApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDbApiService::class.java)
    }

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>> = _popularMovies

    fun loadPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = tmDbApiService.getPopularMovies(BuildConfig.TMDB_API_KEY)
                if (response.isSuccessful && response.body() != null) {
                    _popularMovies.postValue(response.body()!!.results)
                } else {
                    // Gestion des erreurs, par exemple en affichant un message d'erreur ou en mettant à jour un état d'erreur dans le ViewModel
                    _popularMovies.postValue(emptyList())
                }
            } catch (e: Exception) {
                // Gestion des exceptions, par exemple en affichant un message d'erreur ou en mettant à jour un état d'erreur dans le ViewModel
                _popularMovies.postValue(emptyList())
            }
        }
    }
}

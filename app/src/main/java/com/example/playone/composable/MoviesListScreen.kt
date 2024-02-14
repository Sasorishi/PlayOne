package com.example.playone.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.playone.model.Movie
import com.example.playone.viewmodel.MoviesViewModel

@Composable
fun MoviesListScreen(moviesViewModel: MoviesViewModel = viewModel()) {
    // Observe les films du ViewModel
    val movies by moviesViewModel.movies.collectAsState()

    // Affiche la liste des films avec LazyColumn
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(movies) { movie ->
            MovieItem(movie = movie)
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = movie.title, style = MaterialTheme.typography.headlineMedium)
        Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium)
        // Ajoutez d'autres détails sur le film ici
    }
}

package com.example.playone

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.playone.model.Movie
import com.example.playone.viewmodel.MoviesViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest


@Composable
fun HomeScreen() {
    val movieViewModel: MoviesViewModel = viewModel()
    // Observer les films populaires depuis le ViewModel
    val movies = movieViewModel.popularMovies.observeAsState(initial = emptyList()).value

    // Déclencher le chargement des données lorsque le composant est chargé
    LaunchedEffect(key1 = true) {
        movieViewModel.loadPopularMovies()
    }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(movies) { movie ->
            MovieItem(movie = movie) // Assurez-vous que movie est bien un objet Movie
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    val imageUrl = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"
    Log.d(TAG, "MovieItem: $imageUrl")

    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = imageUrl)
                        .allowHardware(false)
                        .build()
                ),
                contentDescription = "Image du film",
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f) // Ratio d'aspect 16:9 pour garder les proportions de l'image
                    .background(Color.Black) // Fond noir pour éviter les bords blancs si l'image est rognée
            )
            Text(text = movie.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

package com.example.playone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.playone.components.MovieItem
import com.example.playone.model.Movie
import com.example.playone.viewmodel.MoviesViewModel

@Composable
fun CatalogueScreen(navController: NavController) {
    val movieViewModel: MoviesViewModel = viewModel()
    // Observer les films populaires depuis le ViewModel
    val movies = movieViewModel.popularMovies.observeAsState(initial = emptyList()).value

    // Déclencher le chargement des données lorsque le composant est chargé
    LaunchedEffect(key1 = true) {
        movieViewModel.loadPopularMovies()
    }

    LazyColumn(
        modifier = Modifier
        .fillMaxSize()
        .fillMaxHeight()
        .background(Color(0xFF022739)),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movies) { movie ->
            MovieItem(movie = movie, navController = navController)
        }
    }
}

package com.example.playone

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.playone.viewmodel.MoviesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.playone.composable.MoviesListScreen

// HomeScreen servant de conteneur pour MoviesListScreen
@Composable
fun HomeScreen(navController: NavHostController) {
    // Instancie le MoviesViewModel ici si vous souhaitez le partager entre plusieurs composables
    val moviesViewModel: MoviesViewModel = viewModel()

    // Vous pouvez ajouter plus de logique ici si votre HomeScreen doit inclure plus que juste la liste des films
    MoviesListScreen(moviesViewModel = moviesViewModel)
}

package com.example.playone

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("catalogue") {
            CatalogueScreen(navController = navController)
        }
        composable("details/{movieId}") { navBackStackEntry ->
            // Récupération de l'ID du film à partir des arguments
            val movieId = navBackStackEntry.arguments?.getString("movieId")
            DetailsScreen(movieId = movieId, navController = navController)
        }
    }
}
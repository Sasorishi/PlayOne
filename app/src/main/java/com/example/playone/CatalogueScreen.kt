package com.example.playone

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun CatalogueScreen(navController: NavController) {
    Box(
        modifier = Modifier
        .fillMaxSize()
        .padding(26.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("PlayOne - Catalogue")
            LazyVerticalGrid(columns = GridCells.Fixed(4)) {
                items(15) {
                    // Ajoutez ici le contenu de chaque élément de la grille
                    // Par exemple :
                    // Text("Item $it")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CataloguesScreenPreview() {
    val navController = rememberNavController()
    CatalogueScreen(navController = navController)
}
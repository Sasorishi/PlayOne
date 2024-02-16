package com.example.playone.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.playone.model.Movie

@Composable
fun MovieItem(movie: Movie, navController: NavController) {
    val imageUrl = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp)
            .padding(8.dp)
            .clickable {
                navController.navigate("details/${movie.id}") // Assurez-vous que le chemin correspond à votre graphique de navigation
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = imageUrl)
                        .allowHardware(false)
                        .build()
                ),
                contentDescription = "Image du film",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f) // Ratio d'aspect 16:9 pour garder les proportions de l'image
                    .background(Color.Black), // Fond noir pour éviter les bords blancs si l'image est rognée
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = movie.title, style = MaterialTheme.typography.headlineMedium, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .shadow(1.dp)
            ) {
                Divider(color = Color((0xFF780000)), thickness = 1.dp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
package com.example.playone.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.playone.viewmodel.MoviesViewModel

@Composable
fun DetailsScreen(movieId: String?, navController: NavController, viewModel: MoviesViewModel = viewModel()) {
    // Déclenchez le chargement des détails du film basé sur l'ID fourni
    LaunchedEffect(movieId) {
        movieId?.let { viewModel.loadMovieDetails(it) }
    }

    // Observez les détails du film depuis le ViewModel
    val movieDetails by viewModel.movieDetails.observeAsState()

    // Affichez les détails du film s'ils sont disponibles
    movieDetails?.let { movie ->
        // Construisez l'URL de l'image en utilisant les détails du film chargés
        val imageUrl = "https://image.tmdb.org/t/p/w500/${movie.poster_path}"
        val genresText = movie.genres.joinToString(separator = "- ") { it.name }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Utilisez l'URL de l'image pour charger et afficher l'affiche du film
                Box {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(data = imageUrl)
                                .build()
                        ),
                        contentDescription = "Image du film ${movie.title}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 16f)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 0.dp,
                                    topEnd = 0.dp,
                                    bottomStart = 16.dp,
                                    bottomEnd = 16.dp
                                )
                            )
                            .background(Color.Black),
                        contentScale = ContentScale.Crop
                    )
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0x80787878)
                        )
                    ) {
                        Text("<", fontSize = 18.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("${movie.title} - ${movie.release_date}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .shadow(2.dp)
                ) {
                    Divider(color = Color((0xFF780000)), thickness = 1.dp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Score : ${movie.popularity} ", // Utilisez les champs appropriés de votre modèle Movie
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = genresText,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 16.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        )
                        .background(Color(0xFF022739)),
                    contentAlignment = Alignment.Center
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        item {
                            Text(
                                "Résumé :",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                movie.overview,
                                color = Color.White,
                                style = TextStyle(lineHeight = 24.sp)
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(32.dp))
                            Button(
                                onClick = { /* Implémentez une action lorsque le bouton est cliqué */ },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFF780000
                                    )
                                )
                            ) {
                                Text("Regarder le film")
                            }
                        }
                    }
                }
            }
        }
    } ?: run {
        // Affichez un message si les détails du film ne sont pas disponibles
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Détails du film non disponibles", color = Color.Red)
        }
    }
}
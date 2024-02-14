package com.example.playone

import android.media.MediaPlayer
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var mediaPlayer: MediaPlayer? = null

        AndroidView(
            factory = { context ->
                SurfaceView(context).apply {
                    holder.addCallback(object : SurfaceHolder.Callback {
                        override fun surfaceCreated(holder: SurfaceHolder) {
                            // Start playing video when surface is created
                            // Replace "R.raw.your_video_file" with the appropriate video resource ID
                            val mediaPlayer = MediaPlayer.create(context, R.raw.background_intro)
                            mediaPlayer.setDisplay(holder)
                            mediaPlayer.isLooping = true // Optional: loop the video
                            mediaPlayer.start()
                        }

                        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                            // Surface size or format changed (not used in this example)
                        }

                        override fun surfaceDestroyed(p0: SurfaceHolder) {
                            mediaPlayer?.release()
                            mediaPlayer = null
                        }
                    })
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xAA000000))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painterResource(id = R.drawable.playone_logo_white),
                contentDescription = "Logo",
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { navController.navigate("catalogue") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF780000),
                contentColor = Color.White)
            ) {
                Text("Commencer")
            }
        }
    }
}

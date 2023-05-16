package com.cristhianbonilla.oraculo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.cristhianbonilla.oraculo.R
import com.cristhianbonilla.oraculo.router.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.magic)
    )

    val scrollState = rememberScrollState()

    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = true,
        restartOnPlay = true
    )

    var text by remember {
        mutableStateOf("")
    }

    val density = LocalDensity.current
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val targetHeight = with(density) { screenHeight.dp.toPx() } * 0.48f

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Espacio para mover el LottieAnimation hacia abajo.
                Spacer(modifier = Modifier.height(20.dp))

                LottieAnimation(
                    composition = composition,
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        // Aquí establecemos la altura máxima al 48% de la altura total disponible.
                        .height(with(density) { targetHeight.toDp() })
                )

                Text(
                    text = "Ingresa tu sueño",
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                    color = Color.White,
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp, max = 400.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    navController.navigate(Screen.DetailScreen.withArgs(text))
                }) {
                    Text(text = "Interpretar tu sueño")
                }
            }
        }
    }
}

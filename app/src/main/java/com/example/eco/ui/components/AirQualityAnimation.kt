package com.example.eco.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*

@Composable
fun AirQualityAnimation(animationRes: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationRes))
    val progress by animateLottieCompositionAsState(composition)

    // Анимация увеличения/уменьшения значка
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(modifier = Modifier.size(100.dp)) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.scale(scale)
        )
    }
}


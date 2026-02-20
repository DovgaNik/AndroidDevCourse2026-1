package dev.dovhan.thegreatestcocktailapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Box(
        modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(R.color.orange), colorResource(R.color.teal)
                    )
                )
            )
            .fillMaxSize(),
    ) {
        content()
    }
}

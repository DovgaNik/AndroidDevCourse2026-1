package dev.dovhan.thegreatestcocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.dovhan.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheGreatestCocktailAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GradientBackground {
                        CocktailImage()
                    }
                }
            }
        }
    }
}

@Composable
fun GradientBackground(content: @Composable () -> Unit) {
    Box(
        Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(

                        colorResource(R.color.teal_700),
                        colorResource(R.color.purple_700)

                    )
                )
            )
            .fillMaxSize()
    ) {
        Unit

    }
}

@Composable
fun CocktailImage() {
    Image(
        painterResource(R.drawable.cocktail),
        contentDescription = "Bon bah, alors",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = Color(200, 100, 200),
                shape = CircleShape,
            )
    )
}

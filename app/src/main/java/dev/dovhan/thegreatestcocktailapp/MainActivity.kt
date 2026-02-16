package dev.dovhan.thegreatestcocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.dovhan.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheGreatestCocktailAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GradientBackground() {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CocktailImage()
                            CocktailName()
                            Row() {
                                InfoTextBox("Other/Unknown")
                                InfoTextBox("Non-alcoholic")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(R.color.orange),
                        colorResource(R.color.teal)
                    )
                )
            )
            .fillMaxSize(),
    ) {
        content()
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
                width = 2.dp,
                color = colorResource(R.color.red),
                shape = CircleShape,
            )
    )
}

@Composable
fun CocktailName() {
    Text(
        text = "✨Cocktail Fancy✨",
        fontSize = 36.sp,
        fontFamily = FontFamily.Cursive,
        color = colorResource(R.color.white),
        modifier = Modifier.padding(top = 16.dp)
    )
}

@Composable
fun InfoTextBox(textToDisplay: String) {
    Box(
        Modifier
            .padding(16.dp)
            .clip(CircleShape)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        colorResource(
                            R.color.red
                        ),
                        colorResource(
                            R.color.orange
                        )
                    )
                )
            )
    ) {
        Text(
            text = textToDisplay,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(10.dp)
        )
    }
}

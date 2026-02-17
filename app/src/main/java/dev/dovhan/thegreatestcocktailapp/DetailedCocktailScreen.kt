package dev.dovhan.thegreatestcocktailapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailedCocktail(innerPadding: PaddingValues) {
    GradientBackground() {
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CocktailImage()
            CocktailName()
            Row() {
                InfoTextBox("Other/Unknown")
                InfoTextBox("Non-alcoholic")
            }
            InfoCard("Ingredients")
            InfoCard("Recipe")
        }
    }
}

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

@Composable
fun CocktailImage() {
    Image(
        painterResource(R.drawable.cocktail),
        contentDescription = "Bon bah, alors",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            //.padding(top = 8.dp)
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
                        ), colorResource(
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

@Composable
fun InfoCard(textToDisplay: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(2.dp, colorResource(R.color.red))
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Text(
                text = textToDisplay,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(stringResource(R.string.lorem_ipsum))
        }
    }
}

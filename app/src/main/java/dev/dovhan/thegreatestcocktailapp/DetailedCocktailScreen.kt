package dev.dovhan.thegreatestcocktailapp

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import dev.dovhan.thegreatestcocktailapp.api.RetrofitClient
import dev.dovhan.thegreatestcocktailapp.api.SingleDrink

@Composable
fun DetailedCocktail(
    innerPadding: PaddingValues,
    cocktailId: String?,
    onDrinkLoaded: ((SingleDrink?) -> Unit)? = null
) {
    var drink by remember { mutableStateOf<SingleDrink?>(null) }
    var isLoading by remember(cocktailId) { mutableStateOf(true) }
    var errorMessage by remember(cocktailId) { mutableStateOf<String?>(null) }

    LaunchedEffect(cocktailId) {
        isLoading = true
        errorMessage = null
        drink = null

        try {
            if (cocktailId != null) {
                drink = RetrofitClient.apiService.getDrink(cocktailId).drinks.firstOrNull()
            } else {
                drink = RetrofitClient.apiService.getRandomDrink().drinks.firstOrNull()
            }
            if (drink == null) {
                errorMessage = "No cocktail found for id $cocktailId"
            }
        } catch (e: Exception) {
            errorMessage = e.message ?: "Failed to load cocktail"
        } finally {
            isLoading = false
            onDrinkLoaded?.invoke(drink)
        }
    }

    when {
        isLoading -> {
            DetailedCocktailMessage(innerPadding, "Loading cocktail...")
        }

        errorMessage != null -> {
            DetailedCocktailMessage(innerPadding, errorMessage!!)
        }

        drink != null -> {
            DetailedCocktailContent(innerPadding, drink!!)
        }
    }
}

@Composable
private fun DetailedCocktailContent(innerPadding: PaddingValues, drink: SingleDrink) {
    val ingredients = drink.ingredientsWithMeasures()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CocktailImage(drink.strDrinkThumb)
        CocktailName(drink.strDrink)
        Row {
            InfoTextBox(drink.strCategory)
            InfoTextBox(drink.strAlcoholic)
        }
        InfoCard(
            title = "Ingredients",
            body = ingredients.ifEmpty { listOf("No ingredients available") }.joinToString("\n")
        )
        InfoCard(title = "Recipe", body = drink.strInstructions)
    }
}

@Composable
private fun DetailedCocktailMessage(innerPadding: PaddingValues, message: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message, color = colorResource(R.color.white), modifier = Modifier.padding(24.dp)
        )
    }
}

private fun SingleDrink.ingredientsWithMeasures(): List<String> {
    val ingredients = listOf(
        strIngredient1,
        strIngredient2,
        strIngredient3,
        strIngredient4,
        strIngredient5,
        strIngredient6,
        strIngredient7,
        strIngredient8,
        strIngredient9,
        strIngredient10,
        strIngredient11,
        strIngredient12,
        strIngredient13,
        strIngredient14,
        strIngredient15
    )
    val measures = listOf(
        strMeasure1,
        strMeasure2,
        strMeasure3,
        strMeasure4,
        strMeasure5,
        strMeasure6,
        strMeasure7,
        strMeasure8,
        strMeasure9,
        strMeasure10,
        strMeasure11,
        strMeasure12,
        strMeasure13,
        strMeasure14,
        strMeasure15
    )

    return ingredients.mapIndexedNotNull { index, ingredient ->
        val cleanedIngredient = ingredient?.trim().orEmpty()
        if (cleanedIngredient.isEmpty()) {
            null
        } else {
            val cleanedMeasure = measures.getOrNull(index)?.trim().orEmpty()
            listOf(cleanedMeasure, cleanedIngredient).filter { it.isNotBlank() }.joinToString(" ")
        }
    }
}

@Composable
fun CocktailImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Cocktail image",
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
fun CocktailName(drinkName: String) {
    Text(
        text = drinkName,
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
                    listOf(colorResource(R.color.red), colorResource(R.color.orange))
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
fun InfoCard(title: String, body: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(2.dp, colorResource(R.color.red))
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = body)
        }
    }
}

package dev.dovhan.thegreatestcocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class DetailedCocktailActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val cocktailId = intent.getStringExtra(EXTRA_COCKTAIL_ID)

        setContent {
            val context = LocalContext.current
            var currentDrink by remember { mutableStateOf<FavoriteDrink?>(null) }
            var isFavorite by remember { mutableStateOf(false) }

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Cocktail App") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent,
                            titleContentColor = colorResource(R.color.white)
                        ),
                        actions = {
                            if (currentDrink != null) {
                                IconButton(
                                    onClick = {
                                        currentDrink?.let { drink ->
                                            isFavorite =
                                                FavoritesManager.toggleFavorite(context, drink)
                                        }
                                    }) {
                                    Icon(
                                        painter = painterResource(R.drawable.heart_icon),
                                        contentDescription = "Favorite Icon",
                                        tint = if (isFavorite) Color.Unspecified else Color.Gray,
                                        modifier = Modifier.padding(4.dp)
                                    )
                                }
                            }
                        }
                    )
                }
            ) { innerPadding ->
                GradientBackground {
                    DetailedCocktail(
                        innerPadding,
                        cocktailId = cocktailId,
                        onDrinkLoaded = { drink ->
                            currentDrink = drink?.let {
                                FavoriteDrink(it.idDrink, it.strDrink, it.strDrinkThumb)
                            }
                            isFavorite = drink?.let {
                                FavoritesManager.isFavorite(context, it.idDrink)
                            } ?: false
                        }
                    )
                }
            }
        }
    }

    companion object {
        const val EXTRA_COCKTAIL_ID = "cocktail_id"
    }
}

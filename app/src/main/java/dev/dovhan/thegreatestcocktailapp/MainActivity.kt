package dev.dovhan.thegreatestcocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.dovhan.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()
            var selectedTab by remember { mutableIntStateOf(0) }

            // Track the currently displayed drink so the heart button can save it
            var currentDrink by remember { mutableStateOf<FavoriteDrink?>(null) }
            var isFavorite by remember { mutableStateOf(false) }

            TheGreatestCocktailAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(
                        title = { Text("Cocktail App") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent,
                            titleContentColor = colorResource(R.color.white)
                        ),
                        actions = {
                            if (selectedTab == 0 && currentDrink != null) {
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
                        })
                }, bottomBar = {
                    NavigationBar {
                        NavigationBarItem(
                            selected = selectedTab == 0,
                            onClick = {
                                selectedTab = 0
                                navController.navigate("cocktail") {
                                    popUpTo("cocktail") { inclusive = true }
                                }
                            },
                            label = { Text("Cocktails") },
                            icon = {
                                Icon(
                                    painterResource(R.drawable.cocktail_icon),
                                    contentDescription = "cocktail icon",
                                    modifier = Modifier.size(32.dp)
                                )
                            })

                        NavigationBarItem(
                            selected = selectedTab == 1,
                            onClick = {
                                selectedTab = 1
                                navController.navigate("categories") {
                                    popUpTo("cocktail")
                                }
                            },
                            label = { Text("Categories") },
                            icon = {
                                Icon(
                                    painterResource(R.drawable.category),
                                    contentDescription = "category icon",
                                    modifier = Modifier.size(32.dp)
                                )
                            })

                        NavigationBarItem(
                            selected = selectedTab == 2,
                            onClick = {
                                selectedTab = 2
                                navController.navigate("favorites") {
                                    popUpTo("cocktail")
                                }
                            },
                            label = { Text("Favorites") },
                            icon = {
                                Icon(
                                    painterResource(R.drawable.heart_icon),
                                    contentDescription = "favorites icon",
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(32.dp)
                                )
                            })
                    }
                }) { innerPadding ->
                    GradientBackground {
                        NavHost(navController, startDestination = "cocktail") {
                            composable("cocktail") {
                                DetailedCocktail(
                                    innerPadding,
                                    cocktailId = null,
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
                            composable("categories") { CategoriesScreen(innerPadding) }
                            composable("favorites") { FavoriteScreen(innerPadding) }
                        }
                    }
                }
            }
        }
    }
}

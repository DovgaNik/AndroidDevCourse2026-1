package dev.dovhan.thegreatestcocktailapp

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.makeText
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
            TheGreatestCocktailAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(
                        title = { Text("Cocktail App") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent,
                            titleContentColor = colorResource(R.color.white)
                        ),
                        actions = {
                            IconButton(
                                {
                                    makeText(
                                        context, "Cocktail added to favs", Toast.LENGTH_SHORT
                                    ).show()
                                }) {
                                Icon(
                                    painter = painterResource(R.drawable.heart_icon),
                                    contentDescription = "Favorite Icon",
                                    tint = Color.Unspecified,
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                        })
                }, bottomBar = {
                    NavigationBar() {
                        NavigationBarItem(selected = true, onClick = {

                            navController.navigate("cocktail")

                        }, label = { Text("Cocktails") }, icon = {
                            Icon(
                                painterResource(R.drawable.cocktail_icon),
                                contentDescription = "cocktail icon",
                                modifier = Modifier.size(32.dp)
                            )
                        })

                        NavigationBarItem(selected = true, onClick = {

                            navController.navigate("categories")

                        }, label = { Text("Categories") }, icon = {
                            Icon(
                                painterResource(R.drawable.category),
                                contentDescription = "cocktail icon",
                                modifier = Modifier.size(32.dp)
                            )
                        })
                    }
                }) { innerPadding ->
                    GradientBackground() {
                        NavHost(navController, startDestination = "cocktail") {
                            composable("cocktail") {
                                DetailedCocktail(innerPadding, cocktailId = null)
                            }
                            composable("categories") { CategoriesScreen(innerPadding) }
                        }
                    }
                }
            }
        }
    }
}

package dev.dovhan.thegreatestcocktailapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.dovhan.thegreatestcocktailapp.ui.theme.TheGreatestCocktailAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheGreatestCocktailAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Cocktail App") },
                            actions = {
                                IconButton(
                                    onClick = {
                                        Log.println(Log.DEBUG, "123", "Button clicked")
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.heart_icon),
                                        contentDescription = "Favorite Icon",
                                        tint = Color.Unspecified,
                                        modifier = Modifier.padding(4.dp)
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    DetailedCocktail(innerPadding)
                }
            }
        }
    }
}

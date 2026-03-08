package dev.dovhan.thegreatestcocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold

class DetailedCocktailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val cocktailId = intent.getStringExtra(EXTRA_COCKTAIL_ID)

        setContent {
            Scaffold { innerPadding ->
                GradientBackground {
                    DetailedCocktail(innerPadding, cocktailId = cocktailId)
                }
            }
        }
    }

    companion object {
        const val EXTRA_COCKTAIL_ID = "cocktail_id"
    }
}


package dev.dovhan.thegreatestcocktailapp

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil3.compose.AsyncImage

@Composable
fun FavoriteScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current
    var favorites by remember { mutableStateOf(FavoritesManager.getAllFavorites(context)) }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                favorites = FavoritesManager.getAllFavorites(context)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    if (favorites.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No favorites yet",
                color = colorResource(R.color.white),
                fontSize = 20.sp,
                modifier = Modifier.padding(24.dp)
            )
        }
    } else {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.padding(innerPadding)
        ) {
            items(favorites) { drink ->
                Card(
                    modifier = Modifier.padding(16.dp),
                    border = BorderStroke(2.dp, colorResource(R.color.black)),
                    onClick = {
                        context.startActivity(
                            Intent(context, DetailedCocktailActivity::class.java)
                                .putExtra(
                                    DetailedCocktailActivity.EXTRA_COCKTAIL_ID,
                                    drink.idDrink
                                )
                        )
                    }
                ) {
                    Column(modifier = Modifier.padding(4.dp)) {
                        Text(
                            text = drink.strDrink,
                            fontSize = 18.sp
                        )
                        AsyncImage(
                            model = drink.strDrinkThumb,
                            contentDescription = "Picture of ${drink.strDrink}"
                        )
                    }
                }
            }
        }
    }
}


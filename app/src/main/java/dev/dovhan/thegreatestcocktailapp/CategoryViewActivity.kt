package dev.dovhan.thegreatestcocktailapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import dev.dovhan.thegreatestcocktailapp.api.RetrofitClient
import dev.dovhan.thegreatestcocktailapp.api.SingleCategory

class CategoryViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { innerPadding ->
                GradientBackground {
                    DrinksList(innerPadding, intent.getStringExtra("category").orEmpty())
                }
            }
        }
    }
}

@Composable
fun DrinksList(innerPadding: PaddingValues, categoryReq: String) {
    val context = LocalContext.current
    var categories by remember { mutableStateOf<List<SingleCategory>>(emptyList()) }

    LaunchedEffect(Unit) {
        categories = RetrofitClient.apiService.getCategory(categoryReq).drinks
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.padding(innerPadding)
    ) {
        items(categories) { category ->
            Card(
                modifier = Modifier.padding(16.dp),
                border = BorderStroke(2.dp, colorResource(R.color.black)),
                onClick = {
                    context.startActivity(
                        Intent(
                            context,
                            dev.dovhan.thegreatestcocktailapp.DetailedCocktailActivity::class.java
                        ).putExtra(
                            dev.dovhan.thegreatestcocktailapp.DetailedCocktailActivity.EXTRA_COCKTAIL_ID,
                            category.idDrink
                        )
                    )
                }
            ) {
                Column(modifier = Modifier.padding(4.dp)) {
                    Text(
                        text = category.strDrink,
                        fontSize = 18.sp
                    )
                    AsyncImage(
                        model = category.strDrinkThumb,
                        contentDescription = "Picture of the cocktail"
                    )
                }
            }
        }
    }
}
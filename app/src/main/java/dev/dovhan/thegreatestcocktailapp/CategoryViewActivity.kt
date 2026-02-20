package dev.dovhan.thegreatestcocktailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.dovhan.thegreatestcocktailapp.api.Category
import dev.dovhan.thegreatestcocktailapp.api.RetrofitClient
import dev.dovhan.thegreatestcocktailapp.api.SingleCategory

class CategoryViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold() { innerPadding ->
                GradientBackground() {
                    drinksList(innerPadding, "Beer")
                }
            }
        }
    }
}

@Composable
fun drinksList(innerPadding: PaddingValues, categoryReq: String) {
    var categories by remember { mutableStateOf<List<SingleCategory>>(emptyList()) }

    LaunchedEffect(Unit) {
        categories = RetrofitClient.apiService.getCategory(categoryReq).drinks
    }

    LazyColumn(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        items(categories) { category ->
            DrinkCard(textToDisplay = category)
        }
    }
}

@Composable
fun DrinkCard(textToDisplay: SingleCategory) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(2.dp, colorResource(R.color.red))
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Text(
                text = textToDisplay.strDrink, fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
            Text(textToDisplay.strDrinkThumb)
        }
    }
}
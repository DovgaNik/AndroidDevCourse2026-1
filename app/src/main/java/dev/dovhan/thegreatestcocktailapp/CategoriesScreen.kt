package dev.dovhan.thegreatestcocktailapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.dovhan.thegreatestcocktailapp.api.Category
import dev.dovhan.thegreatestcocktailapp.api.RetrofitClient

@Composable
fun CategoriesScreen(innerPadding: PaddingValues) {
    var categories by remember { mutableStateOf<List<Category>>(emptyList()) }

    LaunchedEffect(Unit) {
        categories = RetrofitClient.apiService.getCategories().drinks
    }

    LazyColumn(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        items(categories) { category ->
            CategoryItem(textToDisplay = category.strCategory)
        }
    }
}

@Composable
fun CategoryItem(textToDisplay: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(2.dp, colorResource(R.color.black))
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Text(
                text = textToDisplay, fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
        }
    }
}

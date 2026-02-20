package dev.dovhan.thegreatestcocktailapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoriesScreen(innerPadding: PaddingValues) {
    LazyColumn(modifier = Modifier.padding(innerPadding)) {
        item { CategoryItem(textToDisplay = "Coffee") }
        item { CategoryItem(textToDisplay = "Coffee") }
        item { CategoryItem(textToDisplay = "Coffee") }
        item { CategoryItem(textToDisplay = "Coffee") }
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

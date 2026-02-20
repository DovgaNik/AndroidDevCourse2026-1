package dev.dovhan.thegreatestcocktailapp.api

import android.R
import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("drinks") val drinks: List<Category>
)

data class Category(
    @SerializedName("strCategory") val strCategory: String
)

data class SingleCategoryResponse(
    @SerializedName("drinks") val drinks: List<SingleCategory>
)

data class SingleCategory(
    @SerializedName("strDrink") val strDrink: String,
    @SerializedName("strDrinkThumb") val strDrinkThumb: String,
    @SerializedName("idDrink") val idDrink: Int
)


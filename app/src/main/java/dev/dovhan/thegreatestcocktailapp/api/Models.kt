package dev.dovhan.thegreatestcocktailapp.api

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("drinks")
    val drinks: List<Category>
)

data class Category(
    @SerializedName("strCategory")
    val strCategory: String
)


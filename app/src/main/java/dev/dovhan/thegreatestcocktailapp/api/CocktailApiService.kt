package dev.dovhan.thegreatestcocktailapp.api

import android.R
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApiService {
    @GET("list.php?c=list")
    suspend fun getCategories(): CategoriesResponse

    @GET("filter.php")
    suspend fun getCategory(@Query("c") category: String): SingleCategoryResponse
}

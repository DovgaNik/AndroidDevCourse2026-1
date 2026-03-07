package dev.dovhan.thegreatestcocktailapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApiService {
    @GET("list.php?c=list")
    suspend fun getCategories(): CategoriesResponse

    @GET("filter.php")
    suspend fun getCategory(@Query("c") category: String): SingleCategoryResponse

    @GET("lookup.php")
    suspend fun getDrink(@Query("i") id: String): SingleDrinkSet

    @GET("random.php")
    suspend fun getRandomDrink(): SingleDrinkSet
}

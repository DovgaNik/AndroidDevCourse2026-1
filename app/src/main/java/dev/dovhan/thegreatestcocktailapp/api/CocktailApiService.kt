package dev.dovhan.thegreatestcocktailapp.api

import retrofit2.http.GET

interface CocktailApiService {
    @GET("api/json/v1/1/list.php?c=list")
    suspend fun getCategories(): CategoriesResponse
}

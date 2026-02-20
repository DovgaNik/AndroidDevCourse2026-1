package dev.dovhan.thegreatestcocktailapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://www.thecocktaildb.com/"

    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    val apiService: CocktailApiService = retrofit.create(CocktailApiService::class.java)
}


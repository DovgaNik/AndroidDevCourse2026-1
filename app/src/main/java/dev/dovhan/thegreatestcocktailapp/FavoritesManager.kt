package dev.dovhan.thegreatestcocktailapp

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class FavoriteDrink(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)

object FavoritesManager {
    private const val PREFS_NAME = "cocktail_favorites"
    private const val KEY_FAVORITES = "favorites_list"
    private val gson = Gson()

    private fun getPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getAllFavorites(context: Context): List<FavoriteDrink> {
        val json = getPrefs(context).getString(KEY_FAVORITES, null) ?: return emptyList()
        val type = object : TypeToken<List<FavoriteDrink>>() {}.type
        return gson.fromJson(json, type)
    }

    fun isFavorite(context: Context, idDrink: String): Boolean =
        getAllFavorites(context).any { it.idDrink == idDrink }

    fun addFavorite(context: Context, drink: FavoriteDrink) {
        val favorites = getAllFavorites(context).toMutableList()
        if (favorites.none { it.idDrink == drink.idDrink }) {
            favorites.add(drink)
            saveFavorites(context, favorites)
        }
    }

    fun removeFavorite(context: Context, idDrink: String) {
        val favorites = getAllFavorites(context).toMutableList()
        favorites.removeAll { it.idDrink == idDrink }
        saveFavorites(context, favorites)
    }

    fun toggleFavorite(context: Context, drink: FavoriteDrink): Boolean {
        return if (isFavorite(context, drink.idDrink)) {
            removeFavorite(context, drink.idDrink)
            false
        } else {
            addFavorite(context, drink)
            true
        }
    }

    private fun saveFavorites(context: Context, favorites: List<FavoriteDrink>) {
        getPrefs(context).edit()
            .putString(KEY_FAVORITES, gson.toJson(favorites))
            .apply()
    }
}


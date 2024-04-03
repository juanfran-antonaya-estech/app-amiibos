package com.juanfra.ddapp.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.juanfra.ddapp.model.data.GameSerie
import com.juanfra.ddapp.model.data.GameSeries
import java.io.IOException

class Repositorio(val context: Context) {

    private lateinit var currentFragmentName: String
    private var currentPage: Int = 0
    
    fun setCurrentPage (number: Int) {
        currentPage = number
    }
    fun navNext() {
        currentPage++
    }
    fun navBack() {
        currentPage--
    }
    fun getCurrentPage() = currentPage

    fun setCurrentFragmentName(name: String) {
        currentFragmentName = name
    }
    fun getCurrentFragmentName() = currentFragmentName

    fun getAllGameseries() : ArrayList<GameSerie>? {
        val gson = Gson()
        val jsonString = getJsonFromFile(context, "gameseriesmock.json")
        val typeToken = TypeToken.getParameterized(ArrayList::class.java, GameSeries::class.java).type
        val gameSeriesList = gson.fromJson<GameSeries>(jsonString, typeToken)
        return gameSeriesList.amiibo
    }

    fun getJsonFromFile(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}
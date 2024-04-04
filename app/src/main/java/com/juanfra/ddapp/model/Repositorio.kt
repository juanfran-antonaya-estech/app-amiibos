package com.juanfra.ddapp.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.juanfra.ddapp.model.data.gameserieinfo.GameSerie
import com.juanfra.ddapp.model.data.gameserieinfo.GameSeries
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
        val typeToken = TypeToken.getParameterized(GameSeries::class.java).type
        val gameSeriesList = gson.fromJson<GameSeries>(jsonString, typeToken)
        return compressRepeats(ArrayList(gameSeriesList.gameSerie.sortedBy { it.name }))
    }

    private fun compressRepeats(gameSeries: ArrayList<GameSerie>): ArrayList<GameSerie>? {
        val diffnames = getDiff(gameSeries)

        val compressedlist = ArrayList<GameSerie>()

        for(name in diffnames){
            compressedlist.add(GameSerie("", name))
        }
        for(cserie in compressedlist){
            for (gserie in gameSeries) {
                if (cserie.name.equals(gserie.name)) {
                    cserie.key = cserie.key + ",${gserie.key}"
                }
            }
            cserie.key = cserie.key!!.trim(',')
        }

        return compressedlist
    }

    private fun getDiff(gameSeries: ArrayList<GameSerie>): ArrayList<String> {
        val diffnames = arrayListOf<String>()

        for(serie in gameSeries){
            if (!diffnames.contains(serie.name)){
                diffnames.add(serie.name!!)
            }
        }
        return diffnames
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
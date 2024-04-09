package com.juanfra.ddapp.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.juanfra.ddapp.model.data.gameserieinfo.Amiibo
import com.juanfra.ddapp.model.data.gameserieinfo.Amiibos
import com.juanfra.ddapp.model.data.gameserieinfo.GameSerie
import com.juanfra.ddapp.model.data.gameserieinfo.GameSeries
import java.io.IOException

class Repositorio(val context: Context) {

    private var currentFragmentName: String = "Pamplona"
    private var currentPage: Int = 0
    private var currentGameSeriesList = getAllGameseries()
    private var currentAmiiboList = arrayListOf<Amiibo>()
    private var currentAmiibo = Amiibo()

    fun setCurrentGameSerieList(array: ArrayList<GameSerie>) {
        currentGameSeriesList = array
    }

    fun setDefaultList() {
        currentGameSeriesList = getAllGameseries()
    }

    fun getCurrentGameSerieList() : ArrayList<GameSerie> {
        return currentGameSeriesList!!
    }
    
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

    fun setAmiiboList(key: String) {
        val gson = Gson()
        val jsonString = getJsonFromFile(context, "acserie.json")
        val typeToken = TypeToken.getParameterized(Amiibos::class.java).type
        val amiiboList = gson.fromJson<Amiibos>(jsonString, typeToken)
        currentAmiiboList = amiiboList.amiibo
    }

    fun getAmiiboList(): ArrayList<Amiibo>? {
        return ArrayList(currentAmiiboList.sortedBy { it.name }.sortedBy { it.type })
    }
    fun getAmiiboList(key: String): ArrayList<Amiibo>? {
        return getAmiiboList()
    }

    fun getAmiibo(): Amiibo? {
        return currentAmiibo
    }

    fun setAmiibo(amiibo: Amiibo) {
        currentAmiibo = amiibo
    }


    fun setAmiiboList(amiiboList: ArrayList<Amiibo>) {
        currentAmiiboList = amiiboList
    }

}
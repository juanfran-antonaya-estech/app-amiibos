package com.juanfra.ddapp.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.juanfra.ddapp.model.data.gameserieinfo.Amiibo
import com.juanfra.ddapp.model.data.gameserieinfo.Amiibos
import com.juanfra.ddapp.model.data.gameserieinfo.GameSerie
import com.juanfra.ddapp.model.data.gameserieinfo.GameSeries
import com.juanfra.ddapp.model.retrofit.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response
import java.io.IOException

class Repositorio() {

    private val miRetrofit = RetrofitHelper.amiiboService

    private var currentFragmentName: String = "Pamplona"
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

    suspend fun getGameseriesFromAPI(): Response<GameSeries> {
        return miRetrofit.obtenerGameSeries()
    }

    suspend fun getAmiiboListFromApi(): Response<Amiibos> {
        return miRetrofit.obtenerListaAmiibos()
    }

}
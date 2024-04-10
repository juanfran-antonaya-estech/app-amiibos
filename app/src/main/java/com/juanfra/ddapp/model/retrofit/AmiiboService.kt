package com.juanfra.ddapp.model.retrofit

import com.juanfra.ddapp.model.data.gameserieinfo.Amiibos
import com.juanfra.ddapp.model.data.gameserieinfo.GameSeries
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AmiiboService {
    @GET("api/gameseries/")
    suspend fun obtenerGameSeries() : Response<GameSeries>

    @GET("api/amiibo/?showgames&showusage")
    suspend fun obtenerListaAmiibos(@Query("gameseries") name: String) : Response<Amiibos>

}

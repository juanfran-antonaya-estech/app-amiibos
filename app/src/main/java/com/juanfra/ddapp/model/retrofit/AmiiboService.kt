package com.juanfra.ddapp.model.retrofit

import com.juanfra.ddapp.model.data.gameserieinfo.Amiibos
import com.juanfra.ddapp.model.data.gameserieinfo.GameSeries
import retrofit2.Response
import retrofit2.http.GET

interface AmiiboService {
    @GET("api/gameseries/")
    suspend fun obtenerGameSeries() : Response<GameSeries>

    @GET("api/amiibo/?gameseries=0x010&showgames&showusage")
    suspend fun obtenerListaAmiibos() : Response<Amiibos>

}

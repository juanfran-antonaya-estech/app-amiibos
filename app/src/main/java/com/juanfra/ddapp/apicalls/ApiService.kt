package com.juanfra.ddapp.apicalls

import com.juanfra.ddapp.model.data.gameserieinfo.Amiibos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName

interface ApiService {
    @GET("amiibo/?gameseries={id}")
    fun getPostById2(@Query("id") name: String): Call<Amiibos>
    @GET("amiibo/?showgames&showusage")
    fun getPostById(@Query("gameseries") name: String): Call<Amiibos>
}
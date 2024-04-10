package com.juanfra.ddapp.model.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val URL_BASE = "https://www.amiiboapi.com/"

    private val builder = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val amiiboService: AmiiboService by lazy {
        builder.create(AmiiboService::class.java)
    }
}
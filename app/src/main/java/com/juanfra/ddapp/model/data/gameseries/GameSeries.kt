package com.juanfra.ddapp.model.data.gameserieinfo

import com.google.gson.annotations.SerializedName


data class GameSeries (

  @SerializedName("amiibo" ) var gameSerie : ArrayList<GameSerie> = arrayListOf()

)
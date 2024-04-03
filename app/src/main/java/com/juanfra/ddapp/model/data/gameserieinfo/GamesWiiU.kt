package com.juanfra.ddapp.model.data.gameserieinfo

import com.google.gson.annotations.SerializedName


data class GamesWiiU (

  @SerializedName("amiiboUsage" ) var amiiboUsage : ArrayList<AmiiboUsage> = arrayListOf(),
  @SerializedName("gameID"      ) var gameID      : ArrayList<String>      = arrayListOf(),
  @SerializedName("gameName"    ) var gameName    : String?                = null

)
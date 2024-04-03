package com.juanfra.ddapp.model.data.gameserieinfo

import com.google.gson.annotations.SerializedName


data class Amiibo (

  @SerializedName("amiiboSeries" ) var amiiboSeries : String?                = null,
  @SerializedName("character"    ) var character    : String?                = null,
  @SerializedName("gameSeries"   ) var gameSeries   : String?                = null,
  @SerializedName("games3DS"     ) var games3DS     : ArrayList<Games3DS>    = arrayListOf(),
  @SerializedName("gamesSwitch"  ) var gamesSwitch  : ArrayList<GamesSwitch> = arrayListOf(),
  @SerializedName("gamesWiiU"    ) var gamesWiiU    : ArrayList<GamesWiiU>   = arrayListOf(),
  @SerializedName("head"         ) var head         : String?                = null,
  @SerializedName("image"        ) var image        : String?                = null,
  @SerializedName("name"         ) var name         : String?                = null,
  @SerializedName("release"      ) var release      : Release?               = Release(),
  @SerializedName("tail"         ) var tail         : String?                = null,
  @SerializedName("type"         ) var type         : String?                = null

)
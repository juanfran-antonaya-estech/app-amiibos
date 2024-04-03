package com.juanfra.ddapp.model.data.gameserieinfo

import com.google.gson.annotations.SerializedName


data class GameSerie (

  @SerializedName("key"  ) var key  : String? = null,
  @SerializedName("name" ) var name : String? = null

)
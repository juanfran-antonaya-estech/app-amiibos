package com.juanfra.ddapp.model.data.gameserieinfo

import com.google.gson.annotations.SerializedName


data class Release (

  @SerializedName("au" ) var au : String? = null,
  @SerializedName("eu" ) var eu : String? = null,
  @SerializedName("jp" ) var jp : String? = null,
  @SerializedName("na" ) var na : String? = null

)
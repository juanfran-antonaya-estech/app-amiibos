package com.juanfra.ddapp.model.data.gameserieinfo

import com.google.gson.annotations.SerializedName


data class Amiibos (

  @SerializedName("amiibo" ) var amiibo : ArrayList<Amiibo> = arrayListOf()

)
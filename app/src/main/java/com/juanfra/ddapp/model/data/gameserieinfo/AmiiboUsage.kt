package com.juanfra.ddapp.model.data.gameserieinfo

import com.google.gson.annotations.SerializedName


data class AmiiboUsage (

  @SerializedName("Usage" ) var Usage : String?  = null,
  @SerializedName("write" ) var write : Boolean? = null

)
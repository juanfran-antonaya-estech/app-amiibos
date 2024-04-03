package com.juanfra.ddapp.model.data

data class GameSeries (
    val amiibo: ArrayList<GameSerie>
)

data class GameSerie (
    val key: String,
    val name: String
)

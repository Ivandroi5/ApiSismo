package com.example.sismocontrol.data.model

data class Sismo(
    val id: String,
    val lugar: String,
    val magnitud:Double,
    val time: Long = 0,
    val longitud: Double = 0.0,
    val latitud: Double = 0.0
){}



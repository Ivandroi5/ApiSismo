package com.example.sismocontrol.data.repository

import com.example.sismocontrol.data.model.Sismo

interface MainRepository {
    suspend fun fetchSismos(): MutableList<Sismo>
}
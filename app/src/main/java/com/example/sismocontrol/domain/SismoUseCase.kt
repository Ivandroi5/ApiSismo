package com.example.sismocontrol.domain

import com.example.sismocontrol.data.model.Sismo
import com.example.sismocontrol.data.repository.MainRepository

class SismoUseCase(private val repository: MainRepository) {

    suspend fun getAllEqForHour(): MutableList<Sismo>{
        return repository.fetchSismos()
    }

}
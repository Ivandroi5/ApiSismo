package com.example.sismocontrol.data.repository

import com.example.sismocontrol.data.model.Sismo
import com.example.sismocontrol.data.network.SismoApiService
import com.example.sismocontrol.data.network.response.Properties
import com.example.sismocontrol.data.network.response.SismoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainArepositoryImpl: MainRepository {

    private var serviceApi = SismoApiService()

    override suspend fun fetchSismos(): MutableList<Sismo> {
        return withContext(Dispatchers.IO){
            val sismosResponse: SismoResponse = serviceApi.getAllSismos()
            val sismosList = parserSismo(sismosResponse)
            sismosList
        }
    }

    private fun parserSismo(sismoResponse: SismoResponse): MutableList<Sismo>{

        val sismosList = mutableListOf<Sismo>()
        val featuresList = sismoResponse.features

        for(feature in featuresList){

            val properties: Properties = feature.properties
            val id= feature.id

            val magnitude = properties.magnitude
            val place = properties.place
            val time = properties.time

            val geometry = feature.geometry
            val longitude = geometry.longitude
            val latitude = geometry.latitude

            sismosList.add(Sismo(id,place,magnitude,time,longitude,latitude))

        }

        return sismosList

    }

}
package com.example.sismocontrol.data.network

import com.example.sismocontrol.data.network.response.SismoResponse
import retrofit2.http.GET

interface SismoApiClient {

    @GET("all_hour.geojson")
    suspend fun getLastHoursEq(): SismoResponse

}
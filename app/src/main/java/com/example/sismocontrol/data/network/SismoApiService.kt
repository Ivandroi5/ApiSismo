package com.example.sismocontrol.data.network

import com.example.sismocontrol.data.network.response.SismoResponse
import com.example.sismocontrol.domain.RetrofitHelper

class SismoApiService {

    private val helperRetrofit = RetrofitHelper.getRetrofit()

    suspend fun getAllSismos(): SismoResponse{
        val response: SismoResponse = helperRetrofit.create(SismoApiClient::class.java).getLastHoursEq()
        return  response
    }

}
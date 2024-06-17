package com.example.sismocontrol.domain

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit{

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        return Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}
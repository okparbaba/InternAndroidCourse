package com.softwarefactorymm.simplephpapicalling.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiCall {
    val retrofitClient = Retrofit.Builder()
        .baseUrl("http://test.flstudiomyanmar.com/simplephpapi/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}
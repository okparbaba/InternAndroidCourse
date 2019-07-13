package com.softwarefactorymm.loginregister.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerRequest {
    companion object {
        val retrofitClient = Retrofit.Builder()
            .baseUrl("http://api-auth.greenhackersinstitute.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)

        inline fun <reified T> retrofitCli(): T {
            val ret = Retrofit.Builder()
                .baseUrl("http://api-auth.greenhackersinstitute.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(T::class.java)
            return ret
        }
    }
}
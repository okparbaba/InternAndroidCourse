package com.softwarefactorymm.loginregisterretrofit.server

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestInterface {

    @POST("/")
    fun operation(@Body request: ServerRequest): Call<ServerResponse>

}
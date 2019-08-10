package com.softwarefactorymm.loginregister.server

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WebService {
    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String
        ):Call<ServerRespone>

}
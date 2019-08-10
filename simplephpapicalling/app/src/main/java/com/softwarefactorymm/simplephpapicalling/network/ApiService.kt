package com.softwarefactorymm.simplephpapicalling.network

import com.softwarefactorymm.simplephpapicalling.model.PostProduct
import com.softwarefactorymm.simplephpapicalling.model.PostResult
import com.softwarefactorymm.simplephpapicalling.model.Product
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("product/read.php")
    fun getProduct():Call<List<Product>>

    @POST("product/create.php")
    fun createProduct(@Body product: PostProduct):Call<PostResult>
}
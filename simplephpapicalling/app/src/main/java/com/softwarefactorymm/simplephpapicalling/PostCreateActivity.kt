package com.softwarefactorymm.simplephpapicalling

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.softwarefactorymm.simplephpapicalling.model.PostProduct
import com.softwarefactorymm.simplephpapicalling.model.PostResult
import com.softwarefactorymm.simplephpapicalling.model.Product
import com.softwarefactorymm.simplephpapicalling.network.ApiCall
import com.softwarefactorymm.simplephpapicalling.network.ApiService
import kotlinx.android.synthetic.main.activity_post_create.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class PostCreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_create)
        btPost.setOnClickListener {
            progressBar2.visibility = View.VISIBLE
            val pName = etProductName.text.trim().toString()
            val pDesc = etProductDesc.text.trim().toString()
            val pPrice = etProductPrice.text.trim().toString()
            val pCateId = etProductCategoryId.text.trim().toString().toInt()
            val pDate = etProductCreateDate.text.trim().toString()
            val postProduct = PostProduct(pName,pPrice,pDesc,pCateId,pDate)
            doAsync {
                val retrofitCall = ApiCall.retrofitClient.createProduct(postProduct)
                retrofitCall.enqueue(object :Callback<PostResult>{
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        progressBar2?.visibility = View.GONE
                    }

                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        uiThread {
                            toast(response.body()?.message.toString())
                            progressBar2?.visibility = View.GONE
                            finish()

                        }
                    }

                })
            }
        }

    }
}

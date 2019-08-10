package com.softwarefactorymm.simplephpapicalling

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.softwarefactorymm.simplephpapicalling.adapter.ProductRecyclerViewAdapter
import com.softwarefactorymm.simplephpapicalling.model.Product
import com.softwarefactorymm.simplephpapicalling.network.ApiCall
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var productList:MutableList<Product>
    private lateinit var productRecyclerViewAdapter: ProductRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar?.visibility = View.VISIBLE
        productList = ArrayList()
        doAsync {
            val retrofitCall = ApiCall.retrofitClient.getProduct()
            retrofitCall.enqueue(object:Callback<List<Product>>{
                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    uiThread { toast("Getting Product Fail");progressBar.visibility = View.GONE }
                }

                override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                    val list = response.body()
                    var product:Product
                    for (i in 0 until (list?.size ?: 1)){
                        product = Product()
                        product.name = list?.get(i)?.name
                        product.description = list?.get(i)?.description
                        product.price = list?.get(i)?.price
                        productList.add(product)

                    }
                    productRecyclerViewAdapter = ProductRecyclerViewAdapter(productList,this@MainActivity)
                    uiThread {
                        rvProduct.layoutManager = LinearLayoutManager(this@MainActivity)
                        rvProduct.adapter = productRecyclerViewAdapter
                        progressBar?.visibility = View.GONE
                    }
                }

            })
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.actionPost->startActivity(Intent(this@MainActivity,PostCreateActivity::class.java))
            R.id.actionImage->startActivity(Intent(this@MainActivity,ImageUploadActivity::class.java))
        }
        return true
    }

}

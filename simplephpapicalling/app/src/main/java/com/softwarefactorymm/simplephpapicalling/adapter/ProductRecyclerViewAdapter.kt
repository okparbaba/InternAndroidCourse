package com.softwarefactorymm.simplephpapicalling.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.softwarefactorymm.simplephpapicalling.R
import com.softwarefactorymm.simplephpapicalling.model.Product
import kotlinx.android.synthetic.main.product_item.view.*

class ProductRecyclerViewAdapter(private var list:List<Product>,private var context: Context):RecyclerView.Adapter<ProductRecyclerViewAdapter.MyHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_item,p0,false)
        return MyHolder(view)
    }

    override fun getItemCount()=list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: MyHolder, p1: Int) {
        val product = list[p1]
        p0.productName.text = product.name
        p0.productDesc.text = product.description
        p0.productPrice.text = product.price+"$"
    }

    class MyHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val productName = itemView.tvProductName as TextView
        val productDesc = itemView.tvProductDesc as TextView
        val productPrice = itemView.tvProductPrice as TextView
    }
}
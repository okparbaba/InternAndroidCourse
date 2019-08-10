package com.softwarefactorymm.simplephpapicalling.model

data class Product(
    var id: String? = "",
    var name: String? = "",
    var price: String? = "",
    var description: String? = "",
    var category_id: Int? = 1,
    var created:String? = ""
)
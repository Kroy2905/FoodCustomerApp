package com.foodApp.customerapp.models

data class allfoodItemsResponseItem(
    val foodCategory: String,
    val foodDescription: String,
    val foodID: String,
    val foodImgUrl: String,
    val foodPrice: Int,
    val foodTitle: String,
    val id: Int,
    val restaurantID: String
)
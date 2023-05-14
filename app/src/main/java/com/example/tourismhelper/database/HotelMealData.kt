package com.example.tourismhelper.database

data class HotelMealData(
    val mealName : String? = null,
    val mealDescription : String? = null,
    val mealServedFor : String? = null,
    val mealIncludingItems : String? = null,
    val mealPrice : String? = null,
    val breakfirst : Boolean? = null,
    val lunch : Boolean? = null,
    val teaTime : Boolean? = null,
    val dinner : Boolean? = null,
)

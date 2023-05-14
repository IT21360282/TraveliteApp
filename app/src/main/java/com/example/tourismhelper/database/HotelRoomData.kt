package com.example.tourismhelper.database

data class HotelRoomData(
    val roomName : String? = null,
    val roomDescription : String? = null,
    val roomFor : String? = null,
    val roomPayFor : String? = null,
    val roomPrice : String? = null,
    val ac : Boolean? = null,
    val fan : Boolean? = null,
    val wiFi : Boolean? = null,
    val tv : Boolean? = null,
    val hotWater : Boolean? = null,
    val balcony : Boolean? = null,
)

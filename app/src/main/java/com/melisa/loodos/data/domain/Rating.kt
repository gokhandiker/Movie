package com.melisa.loodos.data.domain

import com.squareup.moshi.Json

data class Rating(
    @field:Json(name = "Source") val source:String,
    @field:Json(name = "Value")  val value:String
)
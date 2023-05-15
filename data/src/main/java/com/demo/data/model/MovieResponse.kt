package com.demo.data.model

import com.squareup.moshi.Json

data class MovieResponse(
    @Json(name = "image")
    val image: String = "",
    @Json(name = "description")
    val description: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "title")
    val title: String = "",
    @Json(name = "category")
    val category: String = ""
)





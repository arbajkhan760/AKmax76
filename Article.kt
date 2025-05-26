package com.akgroup76.akmax.model

data class Article(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val author: String = "",
    val coverImageUrl: String = "",
    val price: Double = 0.0,
    val uploaderId: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

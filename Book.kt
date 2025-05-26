package com.akgroup76.akmax.model

data class Book(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val author: String = "",
    val downloadUrl: String = "",
    val price: Double = 0.0,
    val coverImageUrl: String = "",
    val uploaderId: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

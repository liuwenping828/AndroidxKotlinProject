package com.domain.repository.restaurant.model

data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    val coverImgUrl: String,
    val status: String,
    val deliveryFee: Int
)
package com.example.harajtask.model

private var carId = 0

data class Ad(
    val body: String,
    val city: String,
    val commentCount: Int,
    val date: Int,
    val thumbURL: String,
    val title: String,
    val username: String,
    val id: Int = carId++
)
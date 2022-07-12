package com.gonexwind.kotlinrestfulapi.model

import java.util.Date

data class ProductResponse(
    val id: String,
    val name: String,
    val price: Long,
    val quantity: Int,
    val createdAt: Date,
    val updatedAt: Date?
)

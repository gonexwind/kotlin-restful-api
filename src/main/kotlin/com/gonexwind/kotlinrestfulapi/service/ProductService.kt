package com.gonexwind.kotlinrestfulapi.service

import com.gonexwind.kotlinrestfulapi.model.CreateProductRequest
import com.gonexwind.kotlinrestfulapi.model.ListProductRequest
import com.gonexwind.kotlinrestfulapi.model.ProductResponse
import com.gonexwind.kotlinrestfulapi.model.UpdateProductRequest

interface ProductService {
    fun create(createProductRequest: CreateProductRequest): ProductResponse
    fun get(id: String): ProductResponse
    fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse
    fun delete(id: String)
    fun list(listProductRequest: ListProductRequest): List<ProductResponse>
}
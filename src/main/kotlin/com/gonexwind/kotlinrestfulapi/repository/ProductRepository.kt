package com.gonexwind.kotlinrestfulapi.repository

import com.gonexwind.kotlinrestfulapi.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String>
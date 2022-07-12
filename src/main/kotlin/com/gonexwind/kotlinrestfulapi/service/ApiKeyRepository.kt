package com.gonexwind.kotlinrestfulapi.service

import com.gonexwind.kotlinrestfulapi.entity.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository : JpaRepository<ApiKey, String>
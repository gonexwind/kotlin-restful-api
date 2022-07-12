package com.gonexwind.kotlinrestfulapi.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "api_keys")
data class ApiKey(
    @Id
    val id: String
)

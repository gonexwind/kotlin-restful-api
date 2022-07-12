package com.gonexwind.kotlinrestfulapi.service.impl

import com.gonexwind.kotlinrestfulapi.entity.Product
import com.gonexwind.kotlinrestfulapi.error.NotFoundException
import com.gonexwind.kotlinrestfulapi.model.CreateProductRequest
import com.gonexwind.kotlinrestfulapi.model.ListProductRequest
import com.gonexwind.kotlinrestfulapi.model.ProductResponse
import com.gonexwind.kotlinrestfulapi.model.UpdateProductRequest
import com.gonexwind.kotlinrestfulapi.repository.ProductRepository
import com.gonexwind.kotlinrestfulapi.service.ProductService
import com.gonexwind.kotlinrestfulapi.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtil: ValidationUtil
) : ProductService {

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validationUtil.validate(createProductRequest)
        val product = Product(
            id = createProductRequest.id!!,
            name = createProductRequest.name!!,
            price = createProductRequest.price!!,
            quantity = createProductRequest.quantity!!,
            createdAt = Date(),
            updatedAt = null
        )
        productRepository.save(product)
        return convertProductToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = findProductByIdOrNull(id)
        return convertProductToProductResponse(product)
    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = findProductByIdOrNull(id)
        validationUtil.validate(updateProductRequest)
        product.apply {
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            quantity = updateProductRequest.quantity!!
            updatedAt = Date()
        }
        productRepository.save(product)
        return convertProductToProductResponse(product)
    }

    override fun delete(id: String) {
        val product = findProductByIdOrNull(id)
        productRepository.delete(product)
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        val products: List<Product> = page.get().collect(Collectors.toList())
        return products.map { convertProductToProductResponse(it) }
    }

    private fun findProductByIdOrNull(id: String): Product =
        productRepository.findByIdOrNull(id) ?: throw NotFoundException()

    private fun convertProductToProductResponse(product: Product): ProductResponse = ProductResponse(
        id = product.id,
        name = product.name,
        price = product.price,
        quantity = product.quantity,
        createdAt = product.createdAt,
        updatedAt = product.updatedAt
    )
}
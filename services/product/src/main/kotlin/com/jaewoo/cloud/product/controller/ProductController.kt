package com.jaewoo.cloud.product.controller

import com.jaewoo.cloud.api.controller.IProductController
import com.jaewoo.cloud.api.domain.dto.ProductDto
import com.jaewoo.cloud.api.error.exception.InvalidInputException
import com.jaewoo.cloud.api.error.exception.NotfoundException
import com.jaewoo.cloud.product.repository.ProductRepository
import com.mongodb.DuplicateKeyException
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productRepository: ProductRepository
) : IProductController {
    override fun createProduct(dto: ProductDto): ProductDto {
        try {
            val product = dto.toEntity()
            val saveProduct = productRepository.save(product)

            println("===========================================")
            println("saveProduct : ${saveProduct}")
            println("===========================================")
            return saveProduct.toDto()
        } catch (ex: DuplicateKeyException) {
            throw InvalidInputException("Duplicate Product Id: ${dto.productId}")
        }
    }

    override fun getProduct(
        productId: Int
    ): ProductDto? {
        val product = productRepository.findByProductId(productId)
            ?: throw NotfoundException("No productId : $productId")

        val productDto = product.toDto()
        println("===========================================")
        println("Product Dto : ${productDto}")
        println("===========================================")

        return productDto
    }

    override fun deleteProduct(productId: Int) {
        TODO("Not yet implemented")
    }
}
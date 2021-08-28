package com.jaewoo.cloud.product.controller

import com.jaewoo.cloud.api.controller.IProductController
import com.jaewoo.cloud.api.dto.ProductDto
import com.jaewoo.cloud.api.error.exception.InvalidInputException
import com.jaewoo.cloud.api.error.exception.NotfoundException
import com.jaewoo.cloud.product.entity.Product
import com.jaewoo.cloud.product.repository.ProductRepository
import com.mongodb.DuplicateKeyException
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productRepository: ProductRepository
) : IProductController {
    override fun createProduct(dto: ProductDto): ProductDto {
        try {
            val saveProduct = productRepository.save(
                Product(
                    productId = dto.productId,
                    productName = dto.productName,
                    productInfo = dto.productInfo ?: ""
                )
            )

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
    ): ProductDto {
        val product = productRepository.findByProductId(productId)
            ?: throw NotfoundException("No productId : $productId")

        val productDto = product.toDto()
        println("===========================================")
        println("Product Dto : ${productDto}")
        println("===========================================")

        return productDto
    }

    override fun deleteProduct(productId: Int) {
        val findProduct = productRepository.findByProductId(productId) ?: throw NotfoundException("Data not exits")

        println("===========================================")
        println("findProduct : ${findProduct}")
        println("===========================================")
        productRepository.delete(findProduct)
    }
}
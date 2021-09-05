package com.jaewoo.cloud.product.controller

import com.jaewoo.cloud.api.controller.IProductController
import com.jaewoo.cloud.api.dto.ProductDto
import com.jaewoo.cloud.api.error.exception.InvalidInputException
import com.jaewoo.cloud.api.error.exception.NotfoundException
import com.jaewoo.cloud.product.entity.Product
import com.jaewoo.cloud.product.repository.ProductRepository
import com.jaewoo.cloud.util.ServiceUtil
import com.mongodb.DuplicateKeyException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productRepository: ProductRepository,
    private val serviceUtil: ServiceUtil
) : IProductController {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun createProduct(@RequestBody dto: ProductDto): ProductDto {
        try {
            logger.info("=========== p1")
            val saveProduct = productRepository.save(
                Product(
                    productId = dto.productId,
                    productName = dto.productName,
                    productInfo = dto.productInfo
                )
            )
            logger.info("=========== p2")

            logger.info("===========================================")
            logger.info("saveProduct : ${saveProduct}")
            logger.info("===========================================")
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
        productDto.serviceAddress = serviceUtil.getServiceAddress()
        logger.info("===========================================")
        logger.info("Product Dto : ${productDto}")
        logger.info("===========================================")

        return productDto
    }

    override fun deleteProduct(productId: Int) {
        val findProduct = productRepository.findByProductId(productId) ?: throw NotfoundException("Data not exits")

        logger.info("===========================================")
        logger.info("findProduct : ${findProduct}")
        logger.info("===========================================")
        productRepository.delete(findProduct)
    }
}
package com.jaewoo.cloud.composite.controller

import com.jaewoo.cloud.api.controller.ICompositeController
import com.jaewoo.cloud.api.dto.*
import com.jaewoo.cloud.api.error.exception.NotfoundException
import com.jaewoo.cloud.util.ServiceUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestController

@RestController
class CompositeController(
    private val integrateModule: IntegrateModule
) : ICompositeController {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun createComposite(dto: CompositeDto) {
        logger.info("======>> 1")
        val product = ProductDto(dto.productId, dto.productName, dto.productInfo, "")
        integrateModule.createProduct(product)
        dto.recommends.forEach {
            integrateModule.createRecommend(RecommendDto(it.recommendId, dto.productId, it.author, it.content, ""))
        }
        dto.reviews.forEach {
            integrateModule.createReview(ReviewDto(it.reviewId, dto.productId, it.author, it.subject, it.content, ""))
        }
    }

    override fun getComposite(productId: Int): CompositeDto {
        val product = integrateModule.getProduct(productId) ?: throw NotfoundException("No product id : $productId")

        val recommends = integrateModule.getRecommends(productId)
        val reviews = integrateModule.getReviews(productId)

        val serviceAddresses = ServiceAddresses()
        return CompositeDto(productId, productName = product.productName, productInfo = product.productInfo, recommends = recommends, reviews = reviews, serviceAddresses)
    }

    override fun deleteComposite(productId: Int) {
        integrateModule.deleteProduct(productId)
        integrateModule.deleteRecommends(productId)
        integrateModule.deleteReviews(productId)
    }
}
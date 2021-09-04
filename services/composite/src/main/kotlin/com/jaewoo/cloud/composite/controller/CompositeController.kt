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
    private val integrateModule: IntegrateModule,
    private val serviceUtil: ServiceUtil
) : ICompositeController {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun createComposite(dto: CompositeDto) {
        val product = ProductDto(dto.productId, dto.productName, dto.productInfo)
        integrateModule.createProduct(product)
        dto.recommends.forEach {
            integrateModule.createRecommend(RecommendDto(it.recommendId, dto.productId, it.author, it.content))
        }
        dto.reviews.forEach {
            integrateModule.createReview(ReviewDto(it.reviewId, dto.productId, it.author, it.subject, it.content))
        }
    }

    override fun getComposite(productId: Int): CompositeDto {
        val product = integrateModule.getProduct(productId) ?: throw NotfoundException("No product id : $productId")

        val recommends = integrateModule.getRecommends(productId)
        val reviews = integrateModule.getReviews(productId)

        val recommendServiceUrl = if (recommends.isEmpty()) "" else recommends.get(0).serviceAddress
        val reviewServiceUrl = if (reviews.isEmpty()) "" else reviews.get(0).serviceAddress
        val serviceAddressesDto = ServiceAddressesDto(
            serviceUtil.getServiceAddress(),
            product.serviceAddress,
            recommendServiceUrl,
            reviewServiceUrl
        )
        return CompositeDto(productId, productName = product.productName, productInfo = product.productInfo, recommends = recommends, reviews = reviews, serviceAddressesDto)
    }

    override fun deleteComposite(productId: Int) {
        integrateModule.deleteProduct(productId)
        integrateModule.deleteRecommends(productId)
        integrateModule.deleteReviews(productId)
    }
}
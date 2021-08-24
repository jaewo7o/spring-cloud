package com.jaewoo.cloud.composite.controller

import com.jaewoo.cloud.api.controller.ICompositeController
import com.jaewoo.cloud.api.domain.dto.CompositeDto
import com.jaewoo.cloud.api.domain.dto.ProductDto
import com.jaewoo.cloud.api.domain.dto.RecommendDto
import com.jaewoo.cloud.api.domain.dto.ReviewDto
import com.jaewoo.cloud.api.error.exception.NotfoundException
import org.springframework.web.bind.annotation.RestController

@RestController
class CompositeController(
    private val integrateModule: IntegrateModule
) : ICompositeController {
    override fun createComposite(body: CompositeDto) {
        val product = ProductDto(body.productId, body.productName, "")
        integrateModule.createProduct(product)
        body.recommends.forEach {
            integrateModule.createRecommend(RecommendDto(body.productId, it.recommendId, it.author, it.content))
        }
        body.reviews.forEach {
            integrateModule.createReview(ReviewDto(body.productId, it.reviewId, it.author, it.subject, it.content))
        }
    }

    override fun getComposite(productId: Int): CompositeDto {
        val product = integrateModule.getProduct(productId) ?: throw NotfoundException("No product id : $productId")

        val recommends = integrateModule.getRecommends(productId)
        val reviews = integrateModule.getReviews(productId)

        return CompositeDto(productId, productName = product.productName, recommends = recommends, reviews = reviews)
    }

    override fun deleteComposite(productId: Int) {
        integrateModule.deleteProduct(productId)
        integrateModule.deleteRecommends(productId)
        integrateModule.deleteReviews(productId)
    }
}
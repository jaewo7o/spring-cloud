package com.jaewoo.cloud.review.controller

import com.jaewoo.cloud.api.controller.IReviewController
import com.jaewoo.cloud.api.dto.ReviewDto
import com.jaewoo.cloud.api.error.exception.InvalidInputException
import com.jaewoo.cloud.review.entity.Review
import com.jaewoo.cloud.review.repository.ReviewRepository
import com.jaewoo.cloud.util.ServiceUtil
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewController(
    private val reviewRepository: ReviewRepository,
    private val serviceUtil: ServiceUtil
) : IReviewController {
    override fun createReview(@RequestBody dto: ReviewDto) : ReviewDto {
        val saveReview = reviewRepository.save(
            Review(
                dto.reviewId,
                dto.productId,
                dto.author,
                dto.subject,
                dto.content
            )
        )
        return saveReview.toDto()
    }

    override fun getReviews(productId: Int): List<ReviewDto> {
        if (productId < 0) throw InvalidInputException("Invalid productId : $productId")

        return reviewRepository.findByProductId(productId)
            .map {
                it.toDto().also {
                    it.serviceAddress = serviceUtil.getServiceAddress()
                }
            }
    }

    override fun deleteReviews(productId: Int) {
        if (productId < 0) throw InvalidInputException("Invalid productId : $productId")

        reviewRepository.findByProductId(productId)
            .also {
                reviewRepository.deleteAll(it)
            }
    }
}
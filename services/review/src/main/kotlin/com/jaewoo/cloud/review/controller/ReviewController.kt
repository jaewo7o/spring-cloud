package com.jaewoo.cloud.review.controller

import com.jaewoo.cloud.api.controller.IReviewController
import com.jaewoo.cloud.api.domain.dto.ReviewDto
import com.jaewoo.cloud.api.error.exception.InvalidInputException
import com.jaewoo.cloud.review.repository.ReviewRepository
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewController(
    private val reviewRepository: ReviewRepository
) : IReviewController {
    override fun createReview(dto: ReviewDto) :ReviewDto {
        val saveReview = reviewRepository.save(dto.toEntity())
        return saveReview.toDto()
    }

    override fun getReviews(productId: Int): List<ReviewDto> {
        if (productId < 0) throw InvalidInputException("Invalid productId : $productId")

        return reviewRepository.findByProductId(productId)
            .map {
                it.toDto()
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
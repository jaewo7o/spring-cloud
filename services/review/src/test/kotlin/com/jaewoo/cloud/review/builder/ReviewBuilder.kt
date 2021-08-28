package com.jaewoo.cloud.review.builder

import com.jaewoo.cloud.api.domain.dto.ReviewDto
import com.jaewoo.cloud.api.domain.entity.Review


fun buildReviewDto(
    productId: Int = 1,
    reviewId: Int = 1,
    author: String = "author",
    subject: String = "subject",
    content: String = "content"
) = ReviewDto(
    productId = productId,
    reviewId = reviewId,
    author = author,
    subject = subject,
    content = content
)


fun buildReview(
    productId: Int = 1,
    reviewId: Int = 1,
    author: String = "author",
    subject: String = "subject",
    content: String = "content"
) = Review(
    productId = productId,
    reviewId = reviewId,
    author = author,
    subject = subject,
    content = content
)
package com.jaewoo.cloud.review.builder

import com.jaewoo.cloud.api.dto.ReviewDto
import com.jaewoo.cloud.review.entity.Review


fun buildReviewDto(
    reviewId: Int = 81,
    productId: Int = 1,
    author: String = "author",
    subject: String = "subject",
    content: String = "content"
) = ReviewDto(
    reviewId = reviewId,
    productId = productId,
    author = author,
    subject = subject,
    content = content,
    serviceAddress = ""
)


fun buildReview(
    reviewId: Int = 1,
    productId: Int = 1,
    author: String = "author",
    subject: String = "subject",
    content: String = "content"
) = Review(
    reviewId = reviewId,
    productId = productId,
    author = author,
    subject = subject,
    content = content
)
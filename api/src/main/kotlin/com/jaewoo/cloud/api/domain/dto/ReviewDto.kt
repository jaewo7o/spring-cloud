package com.jaewoo.cloud.api.domain.dto

import com.jaewoo.cloud.api.domain.entity.Review

data class ReviewDto(
    val productId: Int,
    val reviewId: Int,
    val author: String,
    val subject: String,
    val content: String
) {
    fun toEntity() = Review(
        productId = productId,
        reviewId = reviewId,
        author = author,
        subject = subject,
        content = content
    )
}
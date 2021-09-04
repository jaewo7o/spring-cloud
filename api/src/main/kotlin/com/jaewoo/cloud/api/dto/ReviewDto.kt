package com.jaewoo.cloud.api.dto

data class ReviewDto(
    val reviewId: Int,
    val productId: Int,
    val author: String,
    val subject: String,
    val content: String
) {
    var serviceAddress: String? = null
}
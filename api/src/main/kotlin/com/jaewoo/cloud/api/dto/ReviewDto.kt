package com.jaewoo.cloud.api.dto

data class ReviewDto(
    val productId: Int,
    val recommendId: Int,
    val author: String,
    val subject: String,
    val content: String
)

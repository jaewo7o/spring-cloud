package com.jaewoo.cloud.api.domain.dto

data class RecommendDto(
    val productId: Int,
    val recommendId: Int,
    val author: String,
    val content: String
)

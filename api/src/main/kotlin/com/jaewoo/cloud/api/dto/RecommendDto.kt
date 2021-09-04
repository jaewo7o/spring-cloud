package com.jaewoo.cloud.api.dto

data class RecommendDto(
    val recommendId: Int,
    val productId: Int,
    val author: String,
    val content: String
) {
    var serviceAddress: String? = null
}

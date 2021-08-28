package com.jaewoo.cloud.api.domain.dto

import com.jaewoo.cloud.api.domain.entity.Recommend

data class RecommendDto(
    val productId: Int,
    val recommendId: Int,
    val author: String,
    val content: String
) {
    fun toEntity() = Recommend(
        productId = productId,
        recommendId = recommendId,
        author = author,
        content = content
    )
}

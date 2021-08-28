package com.jaewoo.cloud.recommend.builder

import com.jaewoo.cloud.api.domain.dto.RecommendDto
import com.jaewoo.cloud.api.domain.entity.Recommend

fun buildRecommendDto() = RecommendDto(
    productId = 1,
    recommendId = 1,
    author = "author",
    content = "content"
)

fun buildRecommend(
    productId: Int,
    recommendId: Int,
    author: String = "Author",
    content: String = "Content"
) = Recommend(
    productId = productId,
    recommendId = recommendId,
    author = author,
    content = content
)

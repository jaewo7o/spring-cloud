package com.jaewoo.cloud.recommend.builder

import com.jaewoo.cloud.api.dto.RecommendDto
import com.jaewoo.cloud.recommend.entity.Recommend

fun buildRecommendDto() = RecommendDto(
    recommendId = 1,
    productId = 1,
    author = "author",
    content = "content",
    serviceAddress = ""
)

fun buildRecommend(
    recommendId: Int,
    productId: Int,
    author: String = "Author",
    content: String = "Content"
) = Recommend(
    recommendId = recommendId,
    productId = productId,
    author = author,
    content = content
)

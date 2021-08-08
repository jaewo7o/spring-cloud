package com.jaewoo.cloud.api.dto

class CompositeDto(
    val productId: Int,
    val productName: String,
    val recommends: List<RecommendDto>,
    val reviews: List<ReviewDto>
)
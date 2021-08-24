package com.jaewoo.cloud.api.domain.dto

import com.jaewoo.cloud.api.domain.entity.Product

data class ProductDto(
    val productId: Int,
    val productName: String,
    val productInfo: String?
) {
    fun toEntity() = Product(
        productId = productId,
        productName = productName,
        productInfo = productInfo ?: ""
    )
}

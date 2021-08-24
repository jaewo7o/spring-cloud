package com.jaewoo.cloud.product.builder

import com.jaewoo.cloud.api.domain.dto.ProductDto
import com.jaewoo.cloud.api.domain.entity.Product

class ProductBuilder {
    fun buildProduct(
        productId: Int = 1,
        productName: String = "productName",
        productInfo: String = "productInfo"
    ) = Product(
        productId = productId,
        productName = productName,
        productInfo = productInfo
    )

    fun buildProductDto(
        productId: Int = 1,
        productName: String = "productName",
        productInfo: String = "productInfo"
    ) = ProductDto(
        productId = productId,
        productName = productName,
        productInfo = productInfo
    )
}
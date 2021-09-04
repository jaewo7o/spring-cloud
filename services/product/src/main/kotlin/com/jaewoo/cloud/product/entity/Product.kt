package com.jaewoo.cloud.product.entity

import com.jaewoo.cloud.api.dto.ProductDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "products")
class Product(
    @Indexed(unique = true)
    val productId: Int,

    var productName: String,

    val productInfo: String

) {
    @Id
    lateinit var id: String

    fun toDto() = ProductDto(
        productId = this.productId,
        productName = this.productName,
        productInfo = this.productInfo
    )

    override fun toString(): String {
        return "Product(productId=$productId, productName='$productName', productInfo='$productInfo')"
    }
}
package com.jaewoo.cloud.api.domain.entity

import com.jaewoo.cloud.api.domain.dto.ProductDto
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

//    @Version
//    lateinit var version: Version

    fun toDto() = ProductDto(
        productId = this.productId,
        productName = this.productName,
        productInfo = this.productInfo
    )
}
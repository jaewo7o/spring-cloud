package com.jaewoo.cloud.recommend.entity

import com.jaewoo.cloud.api.dto.RecommendDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "recommends")
@CompoundIndex(name = "prod-rec-id", unique = true, def = "{'productId': 1, 'recommendId' : 1}")
class Recommend(
    val productId: Int,
    val recommendId: Int,
    val author: String,
    val content: String
) {
    @Id
    lateinit var id: String

    fun toDto() = RecommendDto(
        productId = productId,
        recommendId = recommendId,
        author = author,
        content = content
    )
}
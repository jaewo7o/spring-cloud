package com.jaewoo.cloud.recommend.entity

import com.jaewoo.cloud.api.dto.RecommendDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "recommends")
@CompoundIndex(name = "prod-rec-id", unique = true, def = "{'productId': 1, 'recommendId' : 1}")
class Recommend(
    val recommendId: Int,
    val productId: Int,
    val author: String,
    val content: String
) {
    @Id
    lateinit var id: String

    fun toDto() = RecommendDto(
        productId = productId,
        recommendId = recommendId,
        author = author,
        content = content,
        serviceAddress = ""
    )

    override fun toString(): String {
        return "Recommend(productId=$productId, recommendId=$recommendId, author='$author', content='$content')"
    }
}
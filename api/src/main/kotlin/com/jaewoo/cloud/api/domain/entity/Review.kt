package com.jaewoo.cloud.api.domain.entity

import com.jaewoo.cloud.api.domain.dto.ReviewDto
import org.springframework.data.annotation.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Table

@Entity
@Table(name = "reviews")
class Review(
    private val productId: Int,
    private val reviewId: Int,
    private val author: String,
    private val subject: String,
    private val content: String
) {
    @Id
    @GeneratedValue
    lateinit var id: String

    fun toDto() = ReviewDto(
        productId = productId,
        reviewId = reviewId,
        author = author,
        subject = subject,
        content = content
    )
}
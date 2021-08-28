package com.jaewoo.cloud.review.entity

import com.jaewoo.cloud.api.dto.ReviewDto
import javax.persistence.*

@Entity
@Table(name = "reviews")
class Review(
    var productId: Int,
    @Id
    var reviewId: Int,
    var author: String,
    var subject: String,
    var content: String
) {
    fun toDto() = ReviewDto(
        productId = productId,
        reviewId = reviewId,
        author = author,
        subject = subject,
        content = content
    )
}
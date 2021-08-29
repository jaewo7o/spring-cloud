package com.jaewoo.cloud.review.entity

import com.jaewoo.cloud.api.dto.ReviewDto
import javax.persistence.*

@Entity
@Table(name = "reviews")
class Review(
    @Id
    var reviewId: Int,
    var productId: Int,
    var author: String,
    var subject: String,
    var content: String
) {
    fun toDto() = ReviewDto(
        reviewId = reviewId,
        productId = productId,
        author = author,
        subject = subject,
        content = content
    )
}
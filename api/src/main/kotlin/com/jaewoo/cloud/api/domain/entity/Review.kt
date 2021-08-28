package com.jaewoo.cloud.api.domain.entity

import com.jaewoo.cloud.api.domain.dto.ReviewDto
import org.springframework.data.annotation.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Table

@Entity
@Table(name = "reviews")
public class Review(
    var productId: Int,
    var reviewId: Int,
    var author: String,
    var subject: String,
    var content: String
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
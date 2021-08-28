package com.jaewoo.cloud.review.repository

import com.jaewoo.cloud.api.domain.entity.Review
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : CrudRepository<Review, String> {
    fun findByProductId(productId:Int) : List<Review>
}
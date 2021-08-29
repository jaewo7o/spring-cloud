package com.jaewoo.cloud.review.repository

import com.jaewoo.cloud.review.entity.Review
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : CrudRepository<Review, Int> {
    fun findByProductId(productId:Int) : List<Review>
}
package com.jaewoo.cloud.recommend.repository

import com.jaewoo.cloud.recommend.entity.Recommend
import org.springframework.data.repository.CrudRepository

interface RecommendRepository : CrudRepository<Recommend, String> {
    fun findByProductId(productId: Int) : List<Recommend>
}
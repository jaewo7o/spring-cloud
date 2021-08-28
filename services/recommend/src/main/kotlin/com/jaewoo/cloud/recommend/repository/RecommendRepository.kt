package com.jaewoo.cloud.recommend.repository

import com.jaewoo.cloud.api.domain.entity.Recommend
import org.springframework.data.repository.CrudRepository

interface RecommendRepository : CrudRepository<Recommend, String> {
    fun findByProductId(productId: Int) : List<Recommend>
}
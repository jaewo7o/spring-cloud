package com.jaewoo.cloud.api.controller

import com.jaewoo.cloud.api.dto.CompositeDto
import com.jaewoo.cloud.api.dto.ProductDto
import com.jaewoo.cloud.api.dto.RecommendDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

interface IRecommendController {

    @PostMapping("/recommends")
    fun createRecommend(body:RecommendDto)

    @GetMapping("/recommends/{recommendId}")
    fun getRecommend(@PathVariable recommendId : Int): RecommendDto

    @DeleteMapping("/recommends/{productId}")
    fun deleteRecommend(@PathVariable recommendId : Int): RecommendDto
}
package com.jaewoo.cloud.api.controller

import com.jaewoo.cloud.api.dto.RecommendDto
import org.springframework.web.bind.annotation.*

interface IRecommendController {

    @PostMapping("/recommends")
    fun createRecommend(@RequestBody dto: RecommendDto) : RecommendDto?

    @GetMapping("/recommends")
    fun getRecommends(@RequestParam productId : Int): List<RecommendDto>

    @DeleteMapping("/recommends")
    fun deleteRecommends(@RequestParam productId: Int)
}
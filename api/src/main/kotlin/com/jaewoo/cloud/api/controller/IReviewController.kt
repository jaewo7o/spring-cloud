package com.jaewoo.cloud.api.controller

import com.jaewoo.cloud.api.dto.ReviewDto
import org.springframework.web.bind.annotation.*

interface IReviewController {

    @PostMapping("/reviews")
    fun createReview(@RequestBody dto: ReviewDto) : ReviewDto?

    @GetMapping("/reviews")
    fun getReviews(@RequestParam productId : Int): List<ReviewDto>

    @DeleteMapping("/reviews")
    fun deleteReviews(@RequestParam productId : Int)
}
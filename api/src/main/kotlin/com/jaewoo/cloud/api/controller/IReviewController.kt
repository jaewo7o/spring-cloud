package com.jaewoo.cloud.api.controller

import com.jaewoo.cloud.api.dto.CompositeDto
import com.jaewoo.cloud.api.dto.ProductDto
import com.jaewoo.cloud.api.dto.ReviewDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

interface IReviewController {

    @PostMapping("/reviews")
    fun createReview(body:ReviewDto)

    @GetMapping("/reviews/{productId}")
    fun getReview(@PathVariable productId : Int): ReviewDto

    @DeleteMapping("/reviews/{productId}")
    fun deleteReview(@PathVariable productId : Int): ReviewDto
}
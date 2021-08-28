package com.jaewoo.cloud.api.controller

import com.jaewoo.cloud.api.dto.ProductDto
import org.springframework.web.bind.annotation.*

interface IProductController {

    @PostMapping("/products")
    fun createProduct(@RequestBody dto: ProductDto): ProductDto?

    @GetMapping("/products/{productId}")
    fun getProduct(@PathVariable productId: Int): ProductDto?

    @DeleteMapping("/products/{productId}")
    fun deleteProduct(@PathVariable productId: Int)
}
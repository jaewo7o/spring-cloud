package com.jaewoo.cloud.api.controller

import com.jaewoo.cloud.api.dto.CompositeDto
import com.jaewoo.cloud.api.dto.ProductDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import java.util.*

interface IProductController {

    @PostMapping("/products")
    fun createProduct(body:ProductDto)

    @GetMapping("/products/{productId}")
    fun getProduct(@PathVariable productId : Int): ProductDto?

    @DeleteMapping("/products/{productId}")
    fun deleteProduct(@PathVariable productId : Int)
}
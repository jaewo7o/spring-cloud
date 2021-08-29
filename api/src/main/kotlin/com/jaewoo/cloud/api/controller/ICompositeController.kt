package com.jaewoo.cloud.api.controller

import com.jaewoo.cloud.api.dto.CompositeDto
import org.springframework.web.bind.annotation.*

interface ICompositeController {

    @PostMapping("/composites")
    fun createComposite(@RequestBody dto: CompositeDto)

    @GetMapping("/composites/{productId}")
    fun getComposite(@PathVariable productId : Int): CompositeDto

    @DeleteMapping("/composites/{productId}")
    fun deleteComposite(@PathVariable productId : Int)
}
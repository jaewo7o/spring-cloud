package com.jaewoo.cloud.api.controller

import com.jaewoo.cloud.api.dto.CompositeDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

interface ICompositeController {

    @PostMapping("/composites")
    fun createComposite(body:CompositeDto)

    @GetMapping("/composites/{productId}")
    fun getComposite(@PathVariable productId : Int): CompositeDto

    @DeleteMapping("/composites/{productId}")
    fun deleteComposite(@PathVariable productId : Int)
}
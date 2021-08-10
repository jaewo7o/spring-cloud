package com.jaewoo.cloud.composite.controller

import com.jaewoo.cloud.api.controller.ICompositeController
import com.jaewoo.cloud.api.dto.CompositeDto
import org.springframework.web.bind.annotation.RestController

@RestController
class CompositeController : ICompositeController {
    override fun createComposite(body: CompositeDto) {
        TODO("Not yet implemented")
    }

    override fun getComposite(productId: Int): CompositeDto {
        TODO("Not yet implemented")
    }

    override fun deleteComposite(productId: Int): CompositeDto {
        TODO("Not yet implemented")
    }
}
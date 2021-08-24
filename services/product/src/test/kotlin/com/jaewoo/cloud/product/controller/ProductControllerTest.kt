package com.jaewoo.cloud.product.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.jaewoo.cloud.api.domain.dto.ProductDto
import com.jaewoo.cloud.product.builder.ProductBuilder
import com.jaewoo.cloud.product.repository.ProductRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux.just

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureWebTestClient
internal class ProductControllerTest {

    @Autowired
    lateinit var client: WebTestClient

    @Autowired
    lateinit var mapper: ObjectMapper

    @Autowired
    lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setup() {
        productRepository.deleteAll()
    }

    @Test
    fun `Product 단건조회`() {
        val buildProduct = ProductBuilder().buildProduct()
        val (productId, productName, productInfo) = productRepository.save(buildProduct)

        val url = "/products/$productId"
        val result = client.get()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.productId").isEqualTo(productId)
            .jsonPath("$.productName").isEqualTo(productName)
            .jsonPath("$.productInfo").isEqualTo(productInfo)
            .returnResult().toString()

        println(result)
    }

    @Test
    fun createProduct() {
        val buildProductDto = ProductBuilder().buildProductDto()

        val url = "/products"
        val result = client.post()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .body(just(buildProductDto), ProductDto::class.java)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
//            .jsonPath("$.productId").isEqualTo(productId)
//            .jsonPath("$.productName").isEqualTo(productName)
//            .jsonPath("$.productInfo").isEqualTo(productInfo)
            .returnResult().toString()

        println(result)
    }

    @Test
    fun deleteProduct() {
    }
}
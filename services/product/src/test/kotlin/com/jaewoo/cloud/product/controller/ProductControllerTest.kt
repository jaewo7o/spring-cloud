package com.jaewoo.cloud.product.controller

import com.jaewoo.cloud.api.domain.dto.ProductDto
import com.jaewoo.cloud.product.builder.ProductBuilder
import com.jaewoo.cloud.product.repository.ProductRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureWebTestClient
internal class ProductControllerTest {

    @Autowired
    lateinit var client: WebTestClient

    @Autowired
    lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setup() {
        productRepository.deleteAll()
    }

    @Test
    fun `Product 단건조회`() {
        val buildProduct = ProductBuilder().buildProduct()
        val saveProduct = productRepository.save(buildProduct)

        val url = "/products/${saveProduct.productId}"
        val result = client.get()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.productId").isEqualTo(saveProduct.productId)
            .jsonPath("$.productName").isEqualTo(saveProduct.productName)
            .jsonPath("$.productInfo").isEqualTo(saveProduct.productInfo)
            .returnResult().toString()

        println(result)
    }

    @Test
    fun `Product 단건 저장`() {
        val dto = ProductBuilder().buildProductDto()

        val url = "/products"
        val result = client.post()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(dto), ProductDto::class.java)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.productId").isEqualTo(dto.productId)
            .jsonPath("$.productName").isEqualTo(dto.productName)
            .jsonPath("$.productInfo").isEqualTo(dto.productInfo!!)
            .returnResult().toString()

        println(result)
    }

    @Test
    fun `Product 단건 삭제`() {
        val dto = ProductBuilder().buildProductDto()
        productRepository.save(dto.toEntity())

        val url = "/products/${dto.productId}"
        client.delete()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk

        val findByProductId = productRepository.findByProductId(dto.productId)
        Assertions.assertThat(findByProductId).isNull()
    }

    @Test
    fun deleteProduct() {
    }
}
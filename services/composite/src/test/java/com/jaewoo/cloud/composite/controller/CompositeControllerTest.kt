package com.jaewoo.cloud.composite.controller

import com.jaewoo.cloud.api.dto.ProductDto
import com.jaewoo.cloud.api.dto.RecommendDto
import com.jaewoo.cloud.api.dto.ReviewDto
import com.jaewoo.cloud.api.error.exception.InvalidInputException
import com.jaewoo.cloud.api.error.exception.NotfoundException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
internal class CompositeControllerTest {

    companion object {
        private const val PRODUCT_ID_OK = 1
        private const val PRODUCT_ID_NOT_FOUND = 2
        private const val PRODUCT_ID_INVALID = 3
    }

    @Autowired
    lateinit var client: WebTestClient

    @MockBean
    lateinit var integrateModule : IntegrateModule

    @BeforeEach
    fun setUp() {
        `when`(integrateModule.getProduct(PRODUCT_ID_OK))
            .thenReturn(ProductDto(PRODUCT_ID_OK, "name", null))
        `when`(integrateModule.getRecommends(PRODUCT_ID_OK))
            .thenReturn(listOf(RecommendDto(PRODUCT_ID_OK, 1, "author", "contents")))
        `when`(integrateModule.getReviews(PRODUCT_ID_OK))
            .thenReturn(listOf(ReviewDto(PRODUCT_ID_OK, 1, "author", "subject", "contents")))

        `when`(integrateModule.getProduct(PRODUCT_ID_NOT_FOUND))
            .thenThrow(NotfoundException("No productId : $PRODUCT_ID_NOT_FOUND"))
        `when`(integrateModule.getProduct(PRODUCT_ID_INVALID))
            .thenThrow(InvalidInputException("Invalid productId : $PRODUCT_ID_INVALID"))

    }

    @Test
    fun `Composite 단건조회`() {
        val result = client.get()
            .uri("/composites/$PRODUCT_ID_OK")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.productId").isEqualTo(PRODUCT_ID_OK)
            .jsonPath("$.recommends.length()").isEqualTo(1)
            .jsonPath("$.reviews.length()").isEqualTo(1)
            .returnResult().toString()

        println(result)
    }

    @Test
    fun `Composite 단건조회 - NOT FOUND`() {
        val url = "/composites/$PRODUCT_ID_NOT_FOUND"
        val result = client.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.path").isEqualTo(url)
                .jsonPath("$.message").isEqualTo("No productId : $PRODUCT_ID_NOT_FOUND")
                .returnResult().toString()

        println(result)
    }

    @Test
    fun `Composite 단건조회 - INVALID`() {
        val url = "/composites/$PRODUCT_ID_INVALID"
        val result = client.get()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.path").isEqualTo(url)
            .jsonPath("$.message").isEqualTo("Invalid productId : $PRODUCT_ID_INVALID")
            .returnResult().toString()

        println(result)
    }
}
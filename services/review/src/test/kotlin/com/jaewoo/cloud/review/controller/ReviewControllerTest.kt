package com.jaewoo.cloud.review.controller

import com.jaewoo.cloud.api.dto.ReviewDto
import com.jaewoo.cloud.review.builder.buildReview
import com.jaewoo.cloud.review.builder.buildReviewDto
import com.jaewoo.cloud.review.repository.ReviewRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
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
internal class ReviewControllerTest {

    @Autowired
    lateinit var client: WebTestClient

    @Autowired
    lateinit var reviewRepository: ReviewRepository

    @BeforeEach
    fun setup() {
        reviewRepository.deleteAll()
    }

    @Test
    fun `Review 신규생성`() {
        // given
        val dto = buildReviewDto()

        // when & then
        val url = "/reviews"
        val result = client.post()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(dto), ReviewDto::class.java)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.productId").isEqualTo(dto.productId)
            .jsonPath("$.reviewId").isEqualTo(dto.reviewId)
            .jsonPath("$.author").isEqualTo(dto.author)
            .jsonPath("$.content").isEqualTo(dto.content)
            .returnResult().toString()

        println(result)
    }

    @Test
    fun `Product에 대한 Review목록`() {
//        // given
//        val productId = 1
//        val reviewCount = 5
//
//        (1..reviewCount).map {
//            buildReview(
//                productId,
//                it
//            )
//        }.also {
//            reviewRepository.saveAll(it)
//        }
//
//        // when & then
//        val url = "/reviews"
//        val result = client.get()
//            .uri {
//                it.path(url)
//                    .queryParam("productId", productId)
//                    .build()
//            }
//            .exchange()
//            .expectStatus().isOk
//            .expectHeader().contentType(MediaType.APPLICATION_JSON)
//            .expectBody()
//            .jsonPath("$.length()").isEqualTo(reviewCount)
//            .jsonPath("$[1].productId").isEqualTo(productId)
//            .returnResult().toString()
//
//        println(result)
    }

    @Test
    fun `Review 삭제`() {
        // given
//        val productId = 1
//        val reviewCount = 5
//
//        (1..reviewCount).map {
//            buildReview(
//                productId,
//                it
//            )
//        }.also {
//            reviewRepository.saveAll(it)
//        }
//
//        // when
//        val url = "/reviews"
//        client.delete()
//            .uri {
//                it.path(url)
//                    .queryParam("productId", productId)
//                    .build()
//            }
//            .exchange()
//            .expectStatus().isOk
//
//        // then
//        val findReviews = reviewRepository.findByProductId(productId)
//        Assertions.assertThat(findReviews.size).isEqualTo(0)
    }
}
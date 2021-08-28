package com.jaewoo.cloud.recommend.controller

import com.jaewoo.cloud.api.dto.RecommendDto
import com.jaewoo.cloud.recommend.builder.buildRecommend
import com.jaewoo.cloud.recommend.builder.buildRecommendDto
import com.jaewoo.cloud.recommend.repository.RecommendRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.util.UriBuilder
import reactor.core.publisher.Mono

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureWebTestClient
internal class RecommendControllerTest {

    @Autowired
    lateinit var client: WebTestClient

    @Autowired
    lateinit var recommendRepository: RecommendRepository

    @BeforeEach
    fun setup() {
        recommendRepository.deleteAll()
    }

    @Test
    fun `Recommend 신규생성`() {
        // given
        val dto = buildRecommendDto()

        // when & then
        val url = "/recommends"
        val result = client.post()
            .uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(dto), RecommendDto::class.java)
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.productId").isEqualTo(dto.productId)
            .jsonPath("$.recommendId").isEqualTo(dto.recommendId)
            .jsonPath("$.author").isEqualTo(dto.author)
            .jsonPath("$.content").isEqualTo(dto.content)
            .returnResult().toString()

        println(result)
    }

    @Test
    fun `Product에 대한 Review목록`() {
        // given
        val productId = 1
        val recommendCount = 5

        (1..recommendCount).map {
            buildRecommend(
                productId,
                it
            )
        }.also {
            recommendRepository.saveAll(it)
        }

        // when & then
        val url = "/recommends"
        val result = client.get()
            .uri {
                it.path(url)
                    .queryParam("productId", productId)
                    .build()
            }
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.length()").isEqualTo(recommendCount)
            .jsonPath("$[1].productId").isEqualTo(productId)
            .returnResult().toString()

        println(result)
    }

    @Test
    fun `Recommend 삭제`() {
        // given
        val productId = 1
        val recommendCount = 5

        (1..recommendCount).map {
            buildRecommend(
                productId,
                it
            )
        }.also {
            recommendRepository.saveAll(it)
        }

        // when
        val url = "/recommends"
        client.delete()
            .uri {
                it.path(url)
                    .queryParam("productId", productId)
                    .build()
            }
            .exchange()
            .expectStatus().isOk

        // then
        val findReviews = recommendRepository.findByProductId(productId)
        Assertions.assertThat(findReviews.size).isEqualTo(0)
    }
}
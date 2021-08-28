package com.jaewoo.cloud.composite.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.jaewoo.cloud.api.controller.IProductController
import com.jaewoo.cloud.api.controller.IRecommendController
import com.jaewoo.cloud.api.controller.IReviewController
import com.jaewoo.cloud.api.dto.ProductDto
import com.jaewoo.cloud.api.dto.RecommendDto
import com.jaewoo.cloud.api.dto.ReviewDto
import com.jaewoo.cloud.api.error.exception.InvalidInputException
import com.jaewoo.cloud.api.error.exception.NotfoundException
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Component
class IntegrateModule(
    val restTemplate: RestTemplate,
    val objectMapper: ObjectMapper,

    @Value("\${app.product.protocol}")
    val productProtocol: String,
    @Value("\${app.product.host}")
    val productHost: String,
    @Value("\${app.product.port}")
    val productPort: String,
    @Value("\${app.product.service}")
    val productService: String,

    @Value("\${app.recommend.protocol}")
    val recommendProtocol: String,
    @Value("\${app.recommend.host}")
    val recommendHost: String,
    @Value("\${app.recommend.port}")
    val recommendPort: String,
    @Value("\${app.recommend.service}")
    val recommendService: String,

    @Value("\${app.review.protocol}")
    val reviewProtocol: String,
    @Value("\${app.review.host}")
    val reviewHost: String,
    @Value("\${app.review.port}")
    val reviewPort: String,
    @Value("\${app.review.service}")
    val reviewService: String
) : IProductController, IRecommendController, IReviewController {

    val productServiceUrl = "$productProtocol://$productHost:$productPort/$productService"
    val recommendServiceUrl = "$recommendProtocol://$recommendHost:$recommendPort/$recommendService"
    val reviewServiceUrl = "$reviewProtocol://$reviewHost:$reviewPort/$reviewService"

    fun convertHttpClientException(ex: HttpClientErrorException): RuntimeException {
        when (ex.statusCode) {
            HttpStatus.NOT_FOUND -> return NotfoundException(ex.responseBodyAsString)
            HttpStatus.UNPROCESSABLE_ENTITY -> return InvalidInputException(ex.responseBodyAsString)
            else -> {
                println("Unexpected error : ${ex.statusCode}")
                println("Error body : ${ex.responseBodyAsString}")
                return ex
            }
        }
    }

    override fun createProduct(dto: ProductDto): ProductDto? {
        try {
            val url = productServiceUrl
            val product = restTemplate.postForObject(url, dto, ProductDto::class.java)
            println("#####################################################################")
            println("createProduct: $url")
            println("product: $product")
            println("#####################################################################")

            return product
        } catch (ex: HttpClientErrorException) {
            throw convertHttpClientException(ex)
        }
    }

    override fun getProduct(productId: Int): ProductDto? {
        try {
            val url = "$productServiceUrl/$productId"
            val product = restTemplate.getForObject(url, ProductDto::class.java)
            println("#####################################################################")
            println("getProduct: $url")
            println("product: ${product.toString()}")
            println("#####################################################################")
            return product
        } catch (ex: HttpClientErrorException) {
            throw convertHttpClientException(ex)
        }
    }

    override fun deleteProduct(productId: Int) {
        try {
            val url = "$productServiceUrl/$productId"
            restTemplate.delete(url)
            println("#####################################################################")
            println("getProduct: $url")
            println("productId: $productId")
            println("#####################################################################")
        } catch (ex: HttpClientErrorException) {
            throw convertHttpClientException(ex)
        }
    }

    override fun createRecommend(dto: RecommendDto): RecommendDto? {
        try {
            val url = recommendServiceUrl
            val recommendDto = restTemplate.postForObject(url, dto, RecommendDto::class.java)
            println("#####################################################################")
            println("createRecommend: $url")
            println("recommendDto: $recommendDto")
            println("#####################################################################")

            return recommendDto
        } catch (ex: HttpClientErrorException) {
            throw convertHttpClientException(ex)
        }
    }

    override fun getRecommends(productId: Int): List<RecommendDto> {
        try {
            val url = "$recommendServiceUrl?productId=$productId"
            val recommends = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<RecommendDto>>() {}).body as List<RecommendDto>
            println("#####################################################################")
            println("createRecommend: $url")
            println("prductId : $productId, recommend size : ${recommends.size}")
            println("#####################################################################")

            return recommends
        } catch (ex: HttpClientErrorException) {
            return emptyList()
        }
    }

    override fun deleteRecommends(productId: Int) {
        try {
            val url = "$productServiceUrl/$productId"
            restTemplate.delete(url)
            println("#####################################################################")
            println("getProduct: $url")
            println("productId: $productId")
            println("#####################################################################")
        } catch (ex: HttpClientErrorException) {
            throw convertHttpClientException(ex)
        }
    }

    override fun createReview(dto: ReviewDto): ReviewDto? {
        try {
            val url = reviewServiceUrl
            val review = restTemplate.postForObject(url, dto, ReviewDto::class.java)
            println("#####################################################################")
            println("createReview: $url")
            println("review: $review")
            println("#####################################################################")

            return review
        } catch (ex: HttpClientErrorException) {
            throw convertHttpClientException(ex)
        }
    }

    override fun getReviews(productId: Int): List<ReviewDto> {
        try {
            val url = "$reviewServiceUrl?productId=$productId"
            val reviews = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<ReviewDto>>() {}).body as List<ReviewDto>
            println("#####################################################################")
            println("getReviews: $url")
            println("prductId : $productId, review size : ${reviews.size}")
            println("#####################################################################")

            return reviews
        } catch (ex: HttpClientErrorException) {
            return emptyList()
        }
    }

    override fun deleteReviews(productId: Int) {
        try {
            val url = "$productServiceUrl/$productId"
            restTemplate.delete(url)
            println("#####################################################################")
            println("deleteReviews: $url")
            println("productId: $productId")
            println("#####################################################################")
        } catch (ex: HttpClientErrorException) {
            throw convertHttpClientException(ex)
        }
    }
}
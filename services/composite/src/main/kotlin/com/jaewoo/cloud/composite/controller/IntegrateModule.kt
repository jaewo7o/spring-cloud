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
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    val productServiceUrl = "$productProtocol://$productHost:$productPort/$productService"
    val recommendServiceUrl = "$recommendProtocol://$recommendHost:$recommendPort/$recommendService"
    val reviewServiceUrl = "$reviewProtocol://$reviewHost:$reviewPort/$reviewService"

    fun convertHttpClientException(ex: HttpClientErrorException): RuntimeException {
        when (ex.statusCode) {
            HttpStatus.NOT_FOUND -> return NotfoundException(ex.responseBodyAsString)
            HttpStatus.UNPROCESSABLE_ENTITY -> return InvalidInputException(ex.responseBodyAsString)
            else -> {
                logger.info("Unexpected error : ${ex.statusCode}")
                logger.info("Error body : ${ex.responseBodyAsString}")
                return ex
            }
        }
    }

    override fun createProduct(dto: ProductDto): ProductDto? {
        logger.info("======>3")
        try {
            val url = productServiceUrl
            val product = restTemplate.postForObject(url, dto, ProductDto::class.java)
            logger.info("#####################################################################")
            logger.info("createProduct: $url")
            logger.info("product: ${product.toString()}")
            logger.info("#####################################################################")

            return product
        } catch (ex: HttpClientErrorException) {
            throw convertHttpClientException(ex)
        }
    }

    override fun getProduct(productId: Int): ProductDto? {
        try {
            val url = "$productServiceUrl/$productId"
            val product = restTemplate.getForObject(url, ProductDto::class.java)
            logger.info("#####################################################################")
            logger.info("getProduct: $url")
            logger.info("product: ${product.toString()}")
            logger.info("#####################################################################")
            return product
        } catch (ex: HttpClientErrorException) {
            throw convertHttpClientException(ex)
        }
    }

    override fun deleteProduct(productId: Int) {
        try {
            val url = "$productServiceUrl/$productId"
            restTemplate.delete(url)
            logger.info("#####################################################################")
            logger.info("getProduct: $url")
            logger.info("productId: $productId")
            logger.info("#####################################################################")
        } catch (ex: HttpClientErrorException) {
            throw convertHttpClientException(ex)
        }
    }

    override fun createRecommend(dto: RecommendDto): RecommendDto? {
        try {
            val url = recommendServiceUrl
            val recommendDto = restTemplate.postForObject(url, dto, RecommendDto::class.java)
            logger.info("#####################################################################")
            logger.info("createRecommend: $url")
            logger.info("recommendDto: $recommendDto")
            logger.info("#####################################################################")

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
            logger.info("#####################################################################")
            logger.info("createRecommend: $url")
            logger.info("prductId : $productId, recommend size : ${recommends.size}")
            logger.info("#####################################################################")

            return recommends
        } catch (ex: HttpClientErrorException) {
            return emptyList()
        }
    }

    override fun deleteRecommends(productId: Int) {
        try {
            val url = "$recommendServiceUrl?productId=$productId"
            restTemplate.delete(url)
            logger.info("#####################################################################")
            logger.info("getProduct: $url")
            logger.info("productId: $productId")
            logger.info("#####################################################################")
        } catch (ex: HttpClientErrorException) {
            throw convertHttpClientException(ex)
        }
    }

    override fun createReview(dto: ReviewDto): ReviewDto? {
        try {
            logger.info("review dto input : $dto")

            val url = reviewServiceUrl
            val review = restTemplate.postForObject(url, dto, ReviewDto::class.java)
            logger.info("#####################################################################")
            logger.info("createReview: $url")
            logger.info("review: $review")
            logger.info("#####################################################################")

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
            logger.info("#####################################################################")
            logger.info("getReviews: $url")
            logger.info("prductId : $productId, review size : ${reviews.size}")
            logger.info("#####################################################################")

            return reviews
        } catch (ex: HttpClientErrorException) {
            return emptyList()
        }
    }

    override fun deleteReviews(productId: Int) {
        try {
            val url = "$reviewServiceUrl?productId=$productId"
            restTemplate.delete(url)
            logger.info("#####################################################################")
            logger.info("deleteReviews: $url")
            logger.info("productId: $productId")
            logger.info("#####################################################################")
        } catch (ex: HttpClientErrorException) {
            throw convertHttpClientException(ex)
        }
    }
}
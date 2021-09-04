package com.jaewoo.cloud.recommend.controller

import com.jaewoo.cloud.api.controller.IRecommendController
import com.jaewoo.cloud.api.dto.RecommendDto
import com.jaewoo.cloud.api.error.exception.InvalidInputException
import com.jaewoo.cloud.recommend.entity.Recommend
import com.jaewoo.cloud.recommend.repository.RecommendRepository
import com.jaewoo.cloud.util.ServiceUtil
import org.springframework.web.bind.annotation.RestController

@RestController
class RecommendController(
    private val recommendRepository: RecommendRepository,
    private val serviceUtil: ServiceUtil
) : IRecommendController {
    override fun createRecommend(dto: RecommendDto): RecommendDto {
        val saveRecommend = recommendRepository.save(
            Recommend(
                dto.recommendId,
                dto.productId,
                dto.author,
                dto.content
            )
        )
        return saveRecommend.toDto()
    }

    override fun getRecommends(productId: Int): List<RecommendDto> {
        if (productId < 0) throw InvalidInputException("Invalid productId : $productId")

        return recommendRepository.findByProductId(productId)
            .map {
                it.toDto().also {
                    it.serviceAddress = serviceUtil.getServiceAddress()
                }
            }
    }

    override fun deleteRecommends(productId: Int) {
        if (productId < 0) throw InvalidInputException("Invalid productId : $productId")

        recommendRepository.findByProductId(productId)
            .also { recommendRepository.deleteAll(it) }
    }
}
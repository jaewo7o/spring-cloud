package com.jaewoo.cloud.product.repository

import com.jaewoo.cloud.product.builder.ProductBuilder
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class ProductRepositoryTest {
    @Autowired
    lateinit var productRepository: ProductRepository

    @Test
    fun `Product 단건 저장-조회 테스트`() {
        println("test ====>>>>")
        // given
        val buildProduct = ProductBuilder().buildProduct()
        productRepository.save(buildProduct)

        // when
        val findByProduct = productRepository.findByProductId(buildProduct.productId)!!

        // then
        Assertions.assertThat(findByProduct.productId).isEqualTo(buildProduct.productId)
        Assertions.assertThat(findByProduct.productName).isEqualTo(buildProduct.productName)
        Assertions.assertThat(findByProduct.productInfo).isEqualTo(buildProduct.productInfo)
    }

    @Test
    fun `Product 업데이트 테스트`() {
        // given
        val buildProduct = ProductBuilder().buildProduct()
        var saveProduct = productRepository.save(buildProduct)

        // when
        saveProduct.productName = buildProduct.productName + " UPDATE"
        productRepository.save(saveProduct)

        // when
        val findByProduct = productRepository.findByProductId(buildProduct.productId)!!

        // then
        Assertions.assertThat(findByProduct.productId).isEqualTo(buildProduct.productId)
        Assertions.assertThat(findByProduct.productName).isEqualTo(buildProduct.productName + " UPDATE")
        Assertions.assertThat(findByProduct.productInfo).isEqualTo(buildProduct.productInfo)
    }

    @Test
    fun `Product 삭제`() {
        // given
        val buildProduct = ProductBuilder().buildProduct()
        var saveProduct = productRepository.save(buildProduct)

        // when
        productRepository.deleteById(saveProduct.id)

        // then
        val findById = productRepository.findById(saveProduct.id)
        Assertions.assertThat(findById.isPresent).isFalse()
    }
}
package com.jaewoo.cloud.product.repository

import com.jaewoo.cloud.product.builder.buildProduct
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class ProductRepositoryTest {
    @Autowired
    lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setup() {
        // 전체삭제
        productRepository.deleteAll()
    }

    @Test
    fun `Product 단건 저장-조회 테스트`() {
        // given
        val saveProduct = productRepository.save(buildProduct())

        // when
        val findByProduct = productRepository.findByProductId(saveProduct.productId)!!

        // then
        Assertions.assertThat(findByProduct.productId).isEqualTo(saveProduct.productId)
        Assertions.assertThat(findByProduct.productName).isEqualTo(saveProduct.productName)
        Assertions.assertThat(findByProduct.productInfo).isEqualTo(saveProduct.productInfo)
    }

    @Test
    fun `Product 업데이트 테스트`() {
        // given
        val updateProductName = "UPDATE PRODUCTNAME"
        val saveProduct = productRepository.save(buildProduct())

        // when
        saveProduct.productName = updateProductName
        productRepository.save(saveProduct)

        // when
        val findByProduct = productRepository.findByProductId(saveProduct.productId)!!

        // then
        Assertions.assertThat(findByProduct.productId).isEqualTo(saveProduct.productId)
        Assertions.assertThat(findByProduct.productName).isEqualTo(updateProductName)
        Assertions.assertThat(findByProduct.productInfo).isEqualTo(saveProduct.productInfo)
    }

    @Test
    fun `Product 삭제`() {
        // given
        val saveProduct = productRepository.save(buildProduct())

        // when
        productRepository.deleteById(saveProduct.id)

        // then
        val findById = productRepository.findById(saveProduct.id)
        Assertions.assertThat(findById.isPresent).isFalse()
    }
}
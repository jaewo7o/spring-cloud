package com.jaewoo.cloud.product.repository

import com.jaewoo.cloud.product.entity.Product
import org.springframework.data.repository.PagingAndSortingRepository

interface ProductRepository : PagingAndSortingRepository<Product, String> {
    fun findByProductId(productId: Int): Product?
}
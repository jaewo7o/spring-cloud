package com.jaewoo.cloud.product

import com.jaewoo.cloud.product.repository.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@ComponentScan("com.jaewoo.cloud")
@EnableMongoRepositories
@SpringBootApplication
class Application {
    @Bean
    fun initializeData(
        productRepository: ProductRepository
    ) = CommandLineRunner {
        productRepository.deleteAll()
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

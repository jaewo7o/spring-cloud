package com.jaewoo.cloud.recommend

import com.jaewoo.cloud.recommend.repository.RecommendRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan

@ComponentScan("com.jaewoo.cloud")
@SpringBootApplication
class Application {
    @Bean
    fun initializeData(
        recommendRepository: RecommendRepository
    ) = CommandLineRunner {
        recommendRepository.deleteAll()
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
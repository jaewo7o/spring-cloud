package com.jaewoo.cloud.recommend

import com.jaewoo.cloud.recommend.repository.RecommendRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan

@EnableDiscoveryClient
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
package com.jaewoo.cloud.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.web.client.RestTemplate

@EnableMongoRepositories
@ComponentScan("com.jaewoo.cloud")
@SpringBootApplication
class Application(
    val mongoTemplate: MongoProperties
) {

    @Bean
    fun restTemplate() = RestTemplate()

//    @EventListener(ContextRefreshedEvent.class)
//    fun initIndicesAfterStartup() {
//
//    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
package com.jaewoo.cloud.composite

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.client.RestTemplate

@ComponentScan("com.jaewoo.cloud")
@SpringBootApplication
class Application {

    @Bean
    fun restTemplate() = RestTemplate()
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

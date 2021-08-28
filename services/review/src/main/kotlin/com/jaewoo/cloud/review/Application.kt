package com.jaewoo.cloud.review

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.client.RestTemplate

@ComponentScan("com.jaewoo.cloud")
@SpringBootApplication
open class Application {

    @Bean
    open fun restTemplate() = RestTemplate()
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
package com.jaewoo.cloud.composite

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class Application {

    @Bean
    fun restTemplate() : RestTemplate {
        return RestTemplate()
    }

}

//fun main(args: Array<String>) {
//    //runApplication<Application>(*args)
//    SpringApplication.run(Application::class.java, args)
//}

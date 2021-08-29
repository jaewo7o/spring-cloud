
val springBootVersion = "2.4.3"

plugins {
    id("org.springframework.boot") version "2.4.3" apply false
    id("io.spring.dependency-management") version "1.0.8.RELEASE" apply false

    // kotlin 관련 버전은 settings.gradle.kts 와 gradle.properties 에 선언
    kotlin("jvm")
    kotlin("kapt")
    // The plugin specifies the following annotations: @Component, @Async, @Transactional, @Cacheable and @SpringBootTest.
    kotlin("plugin.spring") apply false
    // The plugin specifies @Entity, @Embeddable and @MappedSuperclass no-arg annotations automatically.
    kotlin("plugin.jpa") apply false
}

repositories {
    mavenCentral()
}

allprojects {
    version = "1.0.0"
}

//
subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-jpa")
    apply(plugin = "kotlin-spring")

    repositories {
        mavenCentral()
    }

    dependencies {
        // Kotlin Dependency
        implementation(kotlin("stdlib"))
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        // Spring Boot
        implementation("org.springframework.boot:spring-boot-starter-webflux:${springBootVersion}")
        implementation("org.springframework.boot:spring-boot-starter-test:${springBootVersion}")

        // jackson library
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.+")

        testImplementation("org.mockito:mockito-inline:2.13.0")
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "11"
            }
            dependsOn(processResources) // kotlin 에서 ConfigurationProperties
        }

        compileTestKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "11"
            }
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

project(":services:composite") {
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.springframework.boot")

    dependencies {
        implementation(project(":api"))
        implementation("org.springframework.boot:spring-boot-starter-actuator:${springBootVersion}")
    }
}

project(":services:product") {
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.springframework.boot")

    dependencies {
        implementation(project(":api"))
        implementation("org.springframework.boot:spring-boot-starter-actuator:${springBootVersion}")
        implementation("org.springframework.boot:spring-boot-starter-data-mongodb:${springBootVersion}")
    }
}

project(":services:recommend") {
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.springframework.boot")

    dependencies {
        implementation(project(":api"))
        implementation("org.springframework.boot:spring-boot-starter-actuator:${springBootVersion}")
        implementation("org.springframework.boot:spring-boot-starter-data-mongodb:${springBootVersion}")
    }
}

project(":services:review") {
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.springframework.boot")

    dependencies {
        implementation(project(":api"))
        implementation("org.springframework.boot:spring-boot-starter-actuator:${springBootVersion}")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa:${springBootVersion}")

        // mariadb
        implementation("org.mariadb.jdbc:mariadb-java-client:2.4.1")
    }
}

project(":cloud:eureka") {
    apply(plugin = "kotlin-spring")
    apply(plugin = "org.springframework.boot")

    val springCloudVersion = "2020.0.3"

    dependencies {
        // note that the BOM coordinates are wrapped with the "platform" keyword
        implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"))
        implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
        implementation("org.glassfish.jaxb:jaxb-runtime")
    }
}
buildscript {
    val springBootVersion = "2.4.3"

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}

plugins {
    kotlin("jvm") version "1.5.21"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "com.jaewoo.cloud.api"
version = "1.0.0"
val springBootVersion = "2.4.3"

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

dependencyManagement {
    dependencies {
        dependency("org.springframework.boot:spring-boot-starter-webflux:${springBootVersion}")
        dependency("org.springframework.boot:spring-boot-starter-test:${springBootVersion}")
    }
}
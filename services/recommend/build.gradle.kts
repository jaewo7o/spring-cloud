plugins {
    kotlin("jvm") version "1.5.21"
    id("org.springframework.boot") version "2.4.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "com.jaewoo.cloud.composite"
version = "1.0.0"
val springBootVersion = "2.4.3"

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":api"))
    implementation(kotlin("stdlib"))

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-test")
}
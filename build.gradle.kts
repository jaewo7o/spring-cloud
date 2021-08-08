val springBootVersion = "2.4.3"

allprojects {
    version = "1.0.0"
}


plugins {
    id("org.springframework.boot") version "2.2.2.RELEASE" apply false
    id("io.spring.dependency-management") version "1.0.8.RELEASE" apply false
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

//
subprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "io.spring.dependency-management")

    //java.sourceCompatibility = JavaVersion.VERSION_11

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib"))
        implementation("org.springframework.boot:spring-boot-starter-webflux:${springBootVersion}")
        implementation("org.springframework.boot:spring-boot-starter-test:${springBootVersion}")
    }
}
//
//project(":api") {
//}
//
//project(":services:composite") {
//}
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

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib"))
        implementation("org.springframework.boot:spring-boot-starter-webflux:${springBootVersion}")
        implementation("org.springframework.boot:spring-boot-starter-test:${springBootVersion}")

        testImplementation("org.mockito:mockito-inline:2.13.0")
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }
            dependsOn(processResources) // kotlin 에서 ConfigurationProperties
        }

        compileTestKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}


//
//project(":api") {
//}
//
//project(":services:composite") {
//}
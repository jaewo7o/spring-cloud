group = "com.jaewoo.cloud.api"

java.sourceCompatibility = JavaVersion.VERSION_11

val springBootVersion = "2.4.3"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:${springBootVersion}")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
}
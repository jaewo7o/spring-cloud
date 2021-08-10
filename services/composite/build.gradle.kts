group = "com.jaewoo.cloud.composite"
version = "1.0.0"

java.sourceCompatibility = JavaVersion.VERSION_11

val springBootVersion = "2.4.3"

dependencies {
    implementation(project(":api"))
    implementation("org.springframework.boot:spring-boot-starter-actuator:${springBootVersion}")
}
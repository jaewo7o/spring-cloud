group = "com.jaewoo.cloud.composite"
version = "1.0.0"

java.sourceCompatibility = JavaVersion.VERSION_11


dependencies {
    implementation(project(":api"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}
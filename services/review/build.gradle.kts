group = "com.jaewoo.cloud.review"
version = "1.0.0"

java.sourceCompatibility = JavaVersion.VERSION_11

val springBootVersion = "2.4.3"

dependencies {
    implementation(project(":api"))

    implementation("org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")

    // mariadb
    implementation("org.mariadb.jdbc:mariadb-java-client:2.4.1")
}
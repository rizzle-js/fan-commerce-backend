description = "fan-md external service"

repositories {
    maven("https://repo.spring.io/release")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")//선언형 Http Client 사용을 위해 WebClient 의존성이 필요하다
    api("com.google.cloud:spring-cloud-gcp-starter-storage:4.5.0")

    testImplementation("io.kotest.extensions:kotest-extensions-wiremock:2.0.0")
}

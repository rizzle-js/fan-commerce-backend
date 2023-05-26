description = "fan-md external service"

repositories {
    maven("https://repo.spring.io/release")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")//선언형 Http Client 사용을 위해 WebClient 의존성이 필요하다
}

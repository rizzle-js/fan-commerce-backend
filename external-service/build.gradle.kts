description = "fan-md external service"

repositories {
    maven("https://repo.spring.io/release")
}

dependencyManagement {
    imports {
        mavenBom("com.google.cloud:spring-cloud-gcp-dependencies:4.5.0")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")//선언형 Http Client 사용을 위해 WebClient 의존성이 필요하다
    implementation("com.google.cloud:spring-cloud-gcp-starter-storage")
}

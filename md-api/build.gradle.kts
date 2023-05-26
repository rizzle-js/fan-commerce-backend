plugins {
    id("com.google.cloud.tools.jib")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":context"))
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    //api docs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

tasks.bootJar {
    enabled = true
    archiveFileName.set("$name-api.jar")
}

/**
 * API 문서화 관련 설정
 */

val snippetsDirectory = file("build/generated-snippets")

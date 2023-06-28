plugins {
    `java-test-fixtures`
    kotlin("plugin.jpa")
    kotlin("plugin.noarg")
    id("org.jetbrains.kotlin.plugin.allopen")
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    implementation(project(":common"))
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.redisson:redisson-spring-boot-starter:3.22.1")
    implementation("it.ozimov:embedded-redis:0.7.3") {
        exclude(group ="org.slf4j", module = "slf4j-simple" )
    }

    //runtimeOnly("mysql:mysql-connector-java")
    runtimeOnly("com.h2database:h2")

    integrationTestImplementation("com.h2database:h2")

    //test fixtures
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testFixturesImplementation(project(":common"))
}

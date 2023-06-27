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
    //runtimeOnly("mysql:mysql-connector-java")
    runtimeOnly("com.h2database:h2")

    integrationTestImplementation("com.h2database:h2")

    //test fixtures
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testFixturesImplementation(project(":common"))
}

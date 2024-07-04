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
    api(project(":shared"))
    implementation(project(":modules:product"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

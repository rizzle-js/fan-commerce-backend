rootProject.name = "fan-shop"

pluginManagement {
    val kotlinVersion = "2.0.0"
    val springBootVersion = "3.3.1"
    val springDependencyVersion = "1.1.5"

    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyVersion

        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion

        //jpa
        kotlin("plugin.jpa") version kotlinVersion
        kotlin("plugin.noarg") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion

        id("com.google.cloud.tools.jib") version "3.4.3"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

include("shared")
include("api")
include("modules:product")
include("modules:order")

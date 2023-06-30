rootProject.name = "melon-fan-md"
include("common")
include("context")
include("application-md-api")
include("application-admin-api")
include("external-service")

pluginManagement {
    val kotlinVersion: String = "1.8.20"
    val springBootVersion: String = "3.1.1"

    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version "1.1.0"

        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion

        //jpa
        kotlin("plugin.jpa") version kotlinVersion
        kotlin("plugin.noarg") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion

        id("com.google.cloud.tools.jib") version "3.3.2"
    }
}
include("internal-config")

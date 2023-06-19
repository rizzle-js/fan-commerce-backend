plugins {
    kotlin("jvm")

    kotlin("plugin.spring")
    kotlin("plugin.serialization")

    id("io.spring.dependency-management") apply false
    id("org.springframework.boot") apply false

    id("com.google.cloud.tools.jib") apply false
}

group = "com.kakaoent"

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "kotlinx-serialization")

    repositories {
        mavenCentral()
        maven("https://repo.spring.io/release")
    }

    val kotestVersion = "5.6.2"

    dependencies {
        implementation(kotlin("stdlib"))

        testImplementation(kotlin("test"))
        testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
        testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
        testImplementation("io.kotest:kotest-property:$kotestVersion")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

/**
 * 스프링 어플리케이션을 동작시키는 프로젝트
 * 어플리케이션 모듈 공통
 * integration test(SpringBootTest 활용)
 */
val springContexts: List<Project> = listOf(
    project(":context"),
    project(":external-service"),
    project(":md-api"),
    project(":admin-api"),
)

configure(springContexts) {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.allopen")

    sourceSets {
        create("integration-test") {
            compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
            runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output

            resources.srcDir("src/integration-test/resources")
            tasks.named("processIntegrationTestResources", Copy::class.java) {
                duplicatesStrategy = DuplicatesStrategy.INCLUDE
            }
        }
    }

    val integrationTestImplementation: Configuration by configurations.getting {
        extendsFrom(configurations.implementation.get(), configurations.testImplementation.get())
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        integrationTestImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.mockito")
        }
        integrationTestImplementation(group = "io.mockk", name = "mockk", version = "1.13.5")
        integrationTestImplementation(
            group = "io.kotest.extensions",
            name = "kotest-extensions-spring",
            version = "1.1.3"
        )
        integrationTestImplementation(group = "com.ninja-squad", name = "springmockk", version = "4.0.2")
    }

    tasks.register<Test>("integration-test") {
        description = "Runs integration tests."
        group = "verification"
        useJUnitPlatform()

        testClassesDirs = sourceSets["integration-test"].output.classesDirs
        classpath = sourceSets["integration-test"].runtimeClasspath

        shouldRunAfter(tasks.test)
    }

}

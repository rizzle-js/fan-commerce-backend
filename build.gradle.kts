plugins {
    kotlin("jvm")

    kotlin("plugin.spring")
    kotlin("plugin.serialization")

    id("io.spring.dependency-management")
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
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
        maven("https://repo.spring.io/release")
    }

    val kotestVersion = "5.6.2"
    extra["snakeyaml.version"] = "2.0"


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
    project(":internal-config"),
    project(":external-service"),
    project(":application-md-api"),
    project(":application-admin-api"),
)

configure(springContexts) {
    apply(plugin = "org.springframework.boot")
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

    dependencyManagement {
        imports {
            mavenBom("com.google.cloud:spring-cloud-gcp-dependencies:4.5.1")
            mavenBom("io.micrometer:micrometer-tracing-bom:latest.release")
        }
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

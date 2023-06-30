import com.google.cloud.tools.jib.api.buildplan.ImageFormat

plugins {
    id("com.google.cloud.tools.jib")
}

sourceSets {
    create("api-docs-test") {
        compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
        runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output

        resources.srcDir("src/api-docs-test/resources")
        tasks.named("processApiDocsTestResources", Copy::class.java) {
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
        }
    }
}

val apiDocsTestImplementation: Configuration by configurations.getting {
    extendsFrom(
        configurations.implementation.get(),
        configurations.testImplementation.get(),
        configurations.integrationTestImplementation.get()
    )
}

dependencies {
    implementation(project(":common"))
    implementation(project(":context"))
    implementation(project(":internal-config"))
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // tracing
    implementation("io.micrometer:micrometer-tracing-bridge-otel")

    // prometheus exporter
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    implementation("org.hibernate:hibernate-micrometer:6.2.5.Final")

    //api docs
    apiDocsTestImplementation(testFixtures(project(":internal-config")))
    apiDocsTestImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

tasks.bootJar {
    enabled = true
    archiveFileName.set("$name-api.jar")
}

/**
 * API 문서화 관련 설정
 */

val snippetsDirectory = file("build/generated-snippets")

tasks.register<Test>("api-docs-test") {
    description = "Runs api docs tests."
    group = "verification"
    useJUnitPlatform()

    testClassesDirs = sourceSets["api-docs-test"].output.classesDirs
    classpath = sourceSets["api-docs-test"].runtimeClasspath
}

val packagedReferenceDirectory = file("build/docs")

tasks.register<Tar>("packageReference") {
    dependsOn("api-docs-test")
    description = "Packages api reference."
    group = "documentation"

    compression = Compression.GZIP
    archiveFileName.set("reference.tgz")
    destinationDirectory.set(packagedReferenceDirectory)
    from(snippetsDirectory)
}

jib {
    from {
        if (project.hasProperty("isAmd64")) {
            image = "gcr.io/distroless/java17-debian11"
        } else {
            image = "arm64v8/eclipse-temurin:17-jre-jammy"
        }
    }
    to {
        image = "melon-fan-md-api"
    }
    container {
        user = "65534"
        ports = listOf("8080")
        environment = mapOf()
        format = ImageFormat.OCI
    }
}
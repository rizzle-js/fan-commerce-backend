fun appModules(): List<Project> {
    return project(":modules").childProjects.values.toList()
}

fun DependencyHandlerScope.implementation(projects: List<Project>) {
    projects.forEach(::implementation)
}

dependencies {
    implementation(project(":core"))
    implementation(project(":shared"))
    implementation(appModules())
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude("org.springframework.boot", "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow") {
        // fixed for UT026010: Buffer pool was not set on WebSocketDeploymentInfo, the default pool will be used
        exclude("io.undertow", "undertow-websockets-jsr")
    }

    runtimeOnly("com.h2database:h2")
}

tasks.test {
    useJUnitPlatform()
}

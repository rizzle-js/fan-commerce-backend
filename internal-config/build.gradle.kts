plugins {
    `java-test-fixtures`
}

dependencies {
    implementation(project(":common"))
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testFixturesImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

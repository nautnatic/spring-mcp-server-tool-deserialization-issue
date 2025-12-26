plugins {
    kotlin("jvm") version "2.2.0"
    kotlin("plugin.spring") version "2.1.0"
}

group = "com.mc.core"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.springframework.ai:spring-ai-starter-mcp-server-webmvc:1.1.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.9")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.20.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.20.0")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom:2.2.0"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}
plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.allopen") version "2.0.21"
    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
}

// Extracted constants for repeated values
val jakartaValidationVersion = "3.1.0"

// Renamed variables for clarity
val platformGroupId: String by project
val platformArtifactId: String by project
val platformVersion: String by project

dependencies {
    // Runtime dependencies
    implementation("io.quarkus:quarkus-container-image-docker")
    implementation(enforcedPlatform("$platformGroupId:$platformArtifactId:$platformVersion"))
    implementation("io.quarkus:quarkus-rest-jackson")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("jakarta.validation:jakarta.validation-api:$jakartaValidationVersion")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-rest")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-jdbc-mssql")
    implementation("io.quarkus:quarkus-agroal")
    implementation("io.quarkus:quarkus-hibernate-orm")

    // Test dependencies
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

group = "org.acme"
version = "1.0.0"

// Java configurations
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

// Logging system property configuration for test tasks
tasks.withType<Test> {
    // Specify the required logging manager for Quarkus
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}

// Configure Open Annotation for specific Quarkus annotations
allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("jakarta.persistence.Entity")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

// Kotlin compiler settings
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
        javaParameters = true
    }
}
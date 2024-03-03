plugins {
    kotlin("jvm") version "1.9.22"
}

group = "ac.weekly"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val kotlinResultVersion: String by project
    implementation("com.michael-bull.kotlin-result:kotlin-result:$kotlinResultVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

plugins {
    kotlin("jvm") version "1.9.20"
}

allprojects {
    group = "me.pjaronski"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        if (project.name != "advent-of-code-base") {
            implementation(project(":advent-of-code-base"))
        }
        if (project.name != "advent-of-code-test") {
            testImplementation(project(":advent-of-code-test"))
        }

        testImplementation(kotlin("test"))
    }

    tasks.test {
        useJUnitPlatform()
    }

    kotlin {
        jvmToolchain(21)
    }
}

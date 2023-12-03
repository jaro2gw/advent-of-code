pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
    id("com.gradle.enterprise") version "3.15.1"
}

gradleEnterprise {
    if (System.getenv("CI") == "true") {
        buildScan {
            publishAlways()
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
        }
    }
}

rootProject.name = "advent-of-code"
include("advent-of-code-base")
include("advent-of-code-2022")
include("advent-of-code-test")

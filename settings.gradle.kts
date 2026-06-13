pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    val kotlinVersion: String by settings
    val komapperVersion: String by settings
    // kspVersion is the full KSP plugin version: "1.9.24-1.0.20" style for KSP releases
    // tied to a Kotlin version, or "2.3.9" style for standalone KSP releases (2.3.0+).
    val kspVersion: String by settings
    plugins {
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
        id("org.komapper.gradle") version komapperVersion
        id("com.google.devtools.ksp") version kspVersion
    }
}

rootProject.name = "compatibility-test"
include("simple")

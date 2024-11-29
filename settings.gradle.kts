pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    val kotlinVersion: String by settings
    val komapperVersion: String by settings
    val kspVersion: String by settings
    plugins {
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
        id("org.komapper.gradle") version komapperVersion
        id("com.google.devtools.ksp") version "$kotlinVersion-$kspVersion"
    }
}

rootProject.name = "compatibility-test"
include("simple")

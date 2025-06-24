import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    idea
    java
    kotlin("jvm")
    id("com.google.devtools.ksp")
}

val komapperVersion: String by project

allprojects {
    apply(plugin = "base")

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "idea")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.google.devtools.ksp")

    dependencies {
        platform("org.komapper:komapper-platform:$komapperVersion").let {
            implementation(it)
            ksp(it)
        }
        implementation("org.komapper:komapper-starter-jdbc")
        implementation("org.komapper:komapper-dialect-h2-jdbc")
        ksp("org.komapper:komapper-processor")

        testImplementation(kotlin("test"))
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.4")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.13.2")
    }

    tasks {
        withType<Test>().configureEach {
            useJUnitPlatform()
        }

        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_11)
            }
        }
    }
}

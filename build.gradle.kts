plugins {
    idea
    java
    kotlin("jvm")
    id("com.diffplug.spotless")
    id("com.google.devtools.ksp")
}

val komapperVersion: String by project
val ktlintVersion: String by project

allprojects {
    apply(plugin = "base")
    apply(plugin = "com.diffplug.spotless")

    spotless {
        kotlin {
            ktlint(ktlintVersion)
            targetExclude("build/**")
        }
        kotlinGradle {
            ktlint(ktlintVersion)
        }
    }

    repositories {
        mavenCentral()
    }

    tasks {
        build {
            dependsOn(spotlessApply)
        }
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
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    }

    tasks {
        withType<Test>().configureEach {
            useJUnitPlatform()
        }

        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
            kotlinOptions.jvmTarget = "11"
        }
    }

    idea {
        module {
            sourceDirs = sourceDirs + file("build/generated/ksp/main/kotlin")
            testSourceDirs = testSourceDirs + file("build/generated/ksp/test/kotlin")
            generatedSourceDirs = generatedSourceDirs + file("build/generated/ksp/main/kotlin") + file("build/generated/ksp/test/kotlin")
        }
    }
}

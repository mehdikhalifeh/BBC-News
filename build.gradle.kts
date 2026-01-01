import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt) apply false
}

subprojects {
    plugins.withId("io.gitlab.arturbosch.detekt") {
        extensions.configure<DetektExtension> {
            buildUponDefaultConfig = true
            allRules = false
            config.setFrom(files(rootProject.file("detekt.yml")))
        }

        dependencies {
            "detektPlugins"(libs.detekt.formatting)
        }
    }
}

tasks.register<JacocoReport>("jacocoCombinedReport") {
    dependsOn(
        ":app:testDebugUnitTest",
        ":app:createDebugCoverageReport",
        ":data:test",
        ":domain:test"
    )

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }

    val excludes =
        listOf(
            "**/R.class",
            "**/R$*.class",
            "**/BuildConfig.*",
            "**/Manifest*.*",
            "**/*Test*.*",
            "android/**/*.*"
        )

    val appClassDirs =
        files(
            fileTree("app/build/tmp/kotlin-classes/debug") {
                exclude(excludes)
            },
            fileTree("app/build/intermediates/javac/debug") {
                exclude(excludes)
            }
        )
    val jvmClassDirs =
        files(
            fileTree("data/build/classes/kotlin/main") {
                exclude(excludes)
            },
            fileTree("data/build/classes/java/main") {
                exclude(excludes)
            },
            fileTree("domain/build/classes/kotlin/main") {
                exclude(excludes)
            },
            fileTree("domain/build/classes/java/main") {
                exclude(excludes)
            }
        )

    classDirectories.setFrom(appClassDirs, jvmClassDirs)

    sourceDirectories.setFrom(
        files(
            "app/src/main/java",
            "app/src/main/kotlin",
            "data/src/main/java",
            "data/src/main/kotlin",
            "domain/src/main/java",
            "domain/src/main/kotlin"
        )
    )

    executionData.setFrom(
        files(
            "app/build/jacoco/testDebugUnitTest.exec",
            fileTree("app/build/outputs/code_coverage/debugAndroidTest/connected") {
                include("**/*.ec")
            },
            "data/build/jacoco/test.exec",
            "domain/build/jacoco/test.exec"
        )
    )
}

tasks.register("jacocoTestReport") {
    group = "verification"
    description = "Runs the combined JaCoCo report for all modules."
    dependsOn("jacocoCombinedReport")
}

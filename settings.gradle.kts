pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (
                requested.id.id == "org.jetbrains.kotlin.android" ||
                requested.id.id == "org.jetbrains.kotlin.jvm"
            ) {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
        }
    }
}

gradle.beforeProject {
    buildscript.configurations.configureEach {
        resolutionStrategy.force(
            "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.9.0",
            "com.google.errorprone:error_prone_annotations:2.30.0"
        )
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BBC News"
include(":app", ":domain", ":data")

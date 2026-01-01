plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.detekt)
    jacoco
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_18)
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.coroutines.core)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.javax.inject)

    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
    testImplementation(libs.okhttp.mockwebserver)
}

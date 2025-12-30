import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.mehdi.bbcnews"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mehdi.bbcnews"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        configureBuildConfigFields(project)
    }

    buildTypes {
        debug {}
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
    packaging {
        resources {
            excludes +=
                setOf(
                    "META-INF/*",
                    "META-INF/DEPENDENCIES",
                    "META-INF/LICENSE",
                    "META-INF/LICENSE.txt",
                    "META-INF/license.txt",
                    "META-INF/NOTICE",
                    "META-INF/NOTICE.txt",
                    "META-INF/notice.txt",
                    "META-INF/ASL2.0",
                    "META-INF/*.kotlin_module"
                )
        }
        jniLibs {
            useLegacyPackaging = true
        }
    }
    kapt {
        correctErrorTypes = true
    }
}

fun configureBuildConfigFields(project: Project) {
    val localPropertiesFile = project.rootProject.file("local.properties")
    val props = Properties()
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { props.load(it) }
    }

    val apiKeyValue = buildConfigLiteral(props.getProperty("API_KEY", ""))
    val urlValue = buildConfigLiteral(props.getProperty("URL", ""))
    project.extensions.getByType<BaseAppModuleExtension>().buildTypes.configureEach {
        buildConfigField("String", "API_KEY", apiKeyValue)
        buildConfigField("String", "URL", urlValue)
    }
}

fun buildConfigLiteral(value: String): String {
    val trimmed = value.trim()
    return if (trimmed.startsWith("\"") && trimmed.endsWith("\"")) {
        trimmed
    } else {
        "\"$trimmed\""
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.34.0")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.34.0")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    testImplementation("io.mockk:mockk:1.13.12")
    testImplementation("app.cash.turbine:turbine:1.1.0")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    androidTestImplementation("io.mockk:mockk-android:1.13.12")
    testImplementation("com.google.truth:truth:1.4.2")
    androidTestImplementation("com.google.truth:truth:1.4.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
}

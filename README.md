# BBC News Project App

## Overview
Android news reader built with a clean, multi-module architecture that consumes the NewsAPI
top-headlines endpoint.

## Requirements
Before running the app, obtain an API key from
https://newsapi.org/docs/endpoints/top-headlines and place it in `gradle.properties`
as described below.

## Features & Tech Stack
- Kotlin (Android + JVM modules)
- Android SDK (minSdk 33, targetSdk 36)
- Clean Architecture (app/data/domain modules)
- MVVM
- Jetpack Compose
- Material Design 3
- Navigation Compose
- Coil (image loading)
- Coroutines & Flow
- Retrofit
- OkHttp (logging interceptor)
- Gson converter
- Dagger Hilt (DI)
- Parcelize
- Detekt (static analysis)
- Jacoco (coverage)
- Gradle Version Catalogs (libs.versions.toml)
- Testing:
  - JUnit 4
  - AndroidX Test (JUnit extension)
  - Espresso
  - AndroidX Arch Core Testing
  - MockK
  - Truth
  - Turbine
  - OkHttp MockWebServer
  - Jetpack Compose UI tests

## Configuration
Add the following to `gradle.properties`:

```
API_KEY="XXX"
URL="https://newsapi.org/v2/"
```

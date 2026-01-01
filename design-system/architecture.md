# Architecture Overview

## System Context
The BBC News app is a modern Android client that consumes the NewsAPI top-headlines
endpoint and presents content using a clean, modular, and testable architecture.

**Primary actors**
- **User** browsing headlines and article details.
- **NewsAPI** as the external content provider.

**Core goals**
- Clear separation of concerns.
- Feature scalability through modular boundaries.
- Fast UI rendering and responsive data pipelines.
- Maintainable, testable, and observable code paths.

## Architectural Style
This project uses **Clean Architecture** with a **multi-module** setup:

```
Presentation (app) -> Domain -> Data -> Network
```

Key traits:
- **Dependency Inversion**: Higher-level policies do not depend on lower-level details.
- **Unidirectional Data Flow**: UI triggers use cases; results flow back to UI.
- **Testability**: Domain is platform-agnostic and easy to unit test.

## Edge-of-Technology Stack
- **Jetpack Compose** for declarative, reactive UI.
- **Kotlin Coroutines + Flow** for async, backpressure-aware data streams.
- **Hilt (Dagger)** for compile-time dependency injection.
- **Retrofit + OkHttp** with JSON conversion for networking.
- **Material 3** design system components.
- **Gradle Version Catalogs** for consistent dependency management.

## High-Level Component Diagram
```
+------------------+         +---------------------+         +------------------+
|   UI (Compose)   |  --->   |   Domain Use Cases  |  --->   |    Repositories  |
+------------------+         +---------------------+         +------------------+
         ^                            |                               |
         |                            v                               v
         |                     +--------------+               +--------------+
         |                     |  Entities    |               |   Data Source|
         |                     +--------------+               | (Network/DB) |
         |                                                    +--------------+
         |                                                           |
         +-----------------------------------------------------------+
                         Results Flow Back to UI
```

## Core Design Principles
- **Modularity**: Each module has a distinct responsibility.
- **Explicit boundaries**: Domain models are not coupled to UI or data frameworks.
- **Single source of truth**: Repository governs data retrieval and caching strategy.
- **Resilience**: Network errors are handled and surfaced gracefully to the UI.
- **Performance**: Lean data models, image caching, and Compose recomposition control.

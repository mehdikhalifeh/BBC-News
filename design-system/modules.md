# Module Guide

This project follows a Clean Architecture, multi-module layout. Modules are organized
by responsibility, with strict dependency direction to keep the codebase maintainable.

## Module Map
- **app** — Presentation layer and Android entry points.
- **domain** — Business logic: entities, use cases, and repository contracts.
- **data** — Implementations of repositories, data sources, and mappers.

## Dependency Rules
```
app    -> domain
app    -> data (only for DI wiring)
data   -> domain

(domain must never depend on app or data)
```

## app (Presentation)
**Responsibilities**
- Jetpack Compose UI screens, navigation, and view models.
- UI state modeling and user interaction handling.
- Dependency graph entry (Hilt setup).

**Key patterns**
- MVVM
- State holders (StateFlow/MutableStateFlow)

## domain (Business Logic)
**Responsibilities**
- Core entities (e.g., Article, Source).
- Use cases (e.g., GetTopHeadlines).
- Repository interfaces (contracts only).

**Why it matters**
- No Android framework dependencies.
- Fast and reliable unit testing.

## data (Data Layer)
**Responsibilities**
- Repository implementations.
- Network data sources (Retrofit services).
- DTO-to-domain mappers.

**Key patterns**
- Data source abstraction (e.g., RemoteDataSource).
- Mapping boundaries to keep domain pure.

## Adding a New Feature
1. **Domain**: define entity + use case + repository contract.
2. **Data**: implement repository + data source + mappers.
3. **App**: add ViewModel + Compose UI + navigation.

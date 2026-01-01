# Data Flow

This document describes the end-to-end flow when the user opens the app and requests
news headlines.

## Sequence (Happy Path)
```
User -> UI (Compose) -> ViewModel -> Use Case -> Repository
     -> RemoteDataSource -> NewsAPI -> RemoteDataSource
     -> Repository -> Use Case -> ViewModel -> UI
```

## Detailed Steps
1. **UI event**: A Composable triggers a refresh or initial load.
2. **ViewModel**: Calls the domain use case and exposes UI state via Flow.
3. **Use Case**: Orchestrates the request and applies business rules.
4. **Repository**: Decides where data comes from (remote, cached, or mixed).
5. **RemoteDataSource**: Executes the network call via Retrofit/OkHttp.
6. **Mapper**: Converts DTOs to domain entities.
7. **Result**: ViewModel updates state; Compose recomposes UI.

## Error & Loading States
- **Loading**: ViewModel exposes loading state to show progress indicators.
- **Error**: Repository wraps network errors and surfaces a user-friendly message.
- **Retry**: UI can trigger the same use case to retry.

## Observability
- Logging is handled via OkHttp interceptors.
- Errors are traceable through repository boundaries.
- Flow streams provide clear lifecycle management.

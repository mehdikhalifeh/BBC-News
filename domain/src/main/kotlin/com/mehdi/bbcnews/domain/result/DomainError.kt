package com.mehdi.bbcnews.domain.result

sealed class DomainError {
    data object Network : DomainError()
    data object NotFound : DomainError()
    data object Unauthorized : DomainError()
    data object Unknown : DomainError()
}

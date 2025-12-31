package com.mehdi.bbcnews.domain.result

sealed class DomainError {
    data class Network(val cause: Throwable? = null) : DomainError()
    data class Http(val code: Int, val message: String? = null) : DomainError()
    data class Unknown(val cause: Throwable? = null) : DomainError()
}

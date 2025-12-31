package com.mehdi.bbcnews.data.error

import com.mehdi.bbcnews.domain.result.DomainError
import retrofit2.HttpException
import java.io.IOException

class DomainErrorMapper {
    fun map(throwable: Throwable): DomainError {
        return when (throwable) {
            is IOException -> DomainError.Network
            is HttpException -> when (throwable.code()) {
                401 -> DomainError.Unauthorized
                404 -> DomainError.NotFound
                else -> DomainError.Unknown
            }
            else -> DomainError.Unknown
        }
    }
}

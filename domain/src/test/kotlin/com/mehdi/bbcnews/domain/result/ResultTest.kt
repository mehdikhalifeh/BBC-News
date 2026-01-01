package com.mehdi.bbcnews.domain.result

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ResultTest {

    @Test
    fun `result states should retain provided data`() {
        val loading: Result<String> = Result.Loading
        val success: Result<String> = Result.Success("payload")
        val error = DomainError.Http(code = 500, message = "Server error")
        val failure: Result<String> = Result.Failure(error)

        assertThat(loading).isEqualTo(Result.Loading)
        assertThat((success as Result.Success).data).isEqualTo("payload")
        assertThat((failure as Result.Failure).error).isEqualTo(error)
    }

    @Test
    fun `domain errors should expose details`() {
        val network = DomainError.Network()
        val http = DomainError.Http(code = 404, message = "Not found")
        val unknown = DomainError.Unknown(IllegalStateException("Boom"))

        assertThat(network).isInstanceOf(DomainError.Network::class.java)
        assertThat(http.code).isEqualTo(404)
        assertThat(http.message).isEqualTo("Not found")
        assertThat(unknown.cause).isInstanceOf(IllegalStateException::class.java)
    }
}

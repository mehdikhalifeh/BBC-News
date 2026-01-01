package com.mehdi.bbcnews.presentation.state

import com.mehdi.bbcnews.presentation.model.NewsResponseUi
import com.mehdi.bbcnews.domain.result.DomainError

sealed class NewsListUiState {
    data object Loading : NewsListUiState()
    data class Content(val response: NewsResponseUi) : NewsListUiState()
    data class Error(val error: DomainError) : NewsListUiState()
}

package com.mehdi.bbcnews.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehdi.bbcnews.domain.result.DomainError
import com.mehdi.bbcnews.domain.result.Result
import com.mehdi.bbcnews.domain.usecase.GetTopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val state: StateFlow<NewsUiState> = _state.asStateFlow()

    private val _selectedArticle = MutableStateFlow<NewsUiModel?>(null)
    val selectedArticle: StateFlow<NewsUiModel?> = _selectedArticle.asStateFlow()

    init {
        fetchHeadlines()
    }

    fun fetchHeadlines() {
        _state.value = NewsUiState.Loading
        viewModelScope.launch {
            when (val result = getTopHeadlinesUseCase()) {
                is Result.Success -> {
                    _state.value = NewsUiState.fromArticles(result.data)
                }
                is Result.Failure -> {
                    _state.value = NewsUiState.Error(result.error.toMessage())
                }
            }
        }
    }

    fun selectArticle(article: NewsUiModel) {
        _selectedArticle.value = article
    }

    private fun DomainError.toMessage(): String {
        return when (this) {
            DomainError.Network -> "Network unavailable"
            DomainError.NotFound -> "No headlines found"
            DomainError.Unauthorized -> "Invalid credentials"
            DomainError.Unknown -> "Unexpected error"
        }
    }
}

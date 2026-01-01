package com.mehdi.bbcnews.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehdi.bbcnews.presentation.model.toUi
import com.mehdi.bbcnews.presentation.state.NewsListUiState
import com.mehdi.bbcnews.domain.result.Result
import com.mehdi.bbcnews.domain.usecase.GetTopHeadlinesUseCase
import com.mehdi.bbcnews.util.Constants.NEWS_SOURCE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getTopHeadlines: GetTopHeadlinesUseCase
) : ViewModel() {

    private val _topHeadlines: MutableStateFlow<NewsListUiState> =
        MutableStateFlow(NewsListUiState.Loading)
    val topHeadlines: StateFlow<NewsListUiState> = _topHeadlines.asStateFlow()

    private var isRefreshing = false

    init {
        loadTopHeadlines()
    }

    fun loadTopHeadlines() {
        viewModelScope.launch {
            fetchHeadlines()
        }
    }

    fun onRefresh() {
        if (isRefreshing) return
        isRefreshing = true
        viewModelScope.launch {
            fetchHeadlines()
            isRefreshing = false
        }
    }

    private suspend fun fetchHeadlines() {
        getTopHeadlines(NEWS_SOURCE).collect { response ->
            val state = when (response) {
                is Result.Success -> NewsListUiState.Content(response.data.toUi())
                is Result.Failure -> NewsListUiState.Error(response.error)
                Result.Loading -> NewsListUiState.Loading
            }
            _topHeadlines.value = state
        }
    }
}

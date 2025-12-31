package com.mehdi.bbcnews.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehdi.bbcnews.component.model.toUi
import com.mehdi.bbcnews.component.state.NewsListUiState
import com.mehdi.bbcnews.domain.result.Result
import com.mehdi.bbcnews.domain.usecase.GetTopHeadlinesUseCase
import com.mehdi.bbcnews.util.Constants.NEWS_SOURCE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getTopHeadlines: GetTopHeadlinesUseCase
) :
    ViewModel() {

    private val _topHeadlines: MutableStateFlow<NewsListUiState> =
        MutableStateFlow(NewsListUiState.Loading)
    val topHeadlines: StateFlow<NewsListUiState> = _topHeadlines

    private var isRefreshing = false

    init {
        loadTopHeadlines()
    }

    fun loadTopHeadlines() = viewModelScope.launch {
        getTopHeadlines(NEWS_SOURCE).collect { response ->
            val state = when (response) {
                is Result.Success -> NewsListUiState.Content(response.data.toUi())
                is Result.Failure -> NewsListUiState.Error(response.error)
                Result.Loading -> NewsListUiState.Loading
            }
            setTopHeadlinesValue(state)
        }
    }

    fun onRefresh() {
        if (!isRefreshing) {
            isRefreshing = true
            viewModelScope.launch {
                loadTopHeadlines()
                isRefreshing = false
            }
        }
    }

    private fun setTopHeadlinesValue(state: NewsListUiState) {
        _topHeadlines.value = state
    }
}

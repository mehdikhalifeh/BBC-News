package com.mehdi.bbcnews.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse
import com.mehdi.bbcnews.domain.usecases.GetTopHeadlines
import com.mehdi.bbcnews.util.Constants.NEWS_SOURCE
import com.mehdi.bbcnews.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsListViewModel @Inject constructor(
    val getTopHeadlines: GetTopHeadlines
) :
    ViewModel() {

    private val _topHeadlines: MutableStateFlow<DataState<BbcNewsResponse>> =
        MutableStateFlow(DataState.Loading)
    var topHeadlines: StateFlow<DataState<BbcNewsResponse>> = _topHeadlines

    private var isRefreshing = false

    init {
        getTopHeadlines()
    }

    fun getTopHeadlines() = viewModelScope.launch {
        getTopHeadlines(NEWS_SOURCE).collect { response ->
            when (response) {
                is DataState.Success -> {
                    setTopHeadlinesValue(response)
                }

                else -> setTopHeadlinesValue(response)
            }
        }
    }

    fun onRefresh() {
        if (!isRefreshing) {
            isRefreshing = true
            viewModelScope.launch {
                getTopHeadlines()
                isRefreshing = false
            }
        }
    }

    private fun setTopHeadlinesValue(state: DataState<BbcNewsResponse>) {
        _topHeadlines.value = state
    }
}

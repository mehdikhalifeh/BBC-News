package com.mehdi.bbcnews.component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse
import com.mehdi.bbcnews.domain.usecases.GetTopHeadlines
import com.mehdi.bbcnews.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(private val getTopHeadlines: GetTopHeadlines) :
    ViewModel() {

    private val _topHeadlines: MutableStateFlow<DataState<BbcNewsResponse>> =
        MutableStateFlow(DataState.Loading)
    val topHeadlines: StateFlow<DataState<BbcNewsResponse>> = _topHeadlines

    fun getTopHeadlines(source: String) {
        viewModelScope.launch {
            getTopHeadlines.call(source = source).collect { source ->
                _topHeadlines.value = source
            }
        }
    }
}
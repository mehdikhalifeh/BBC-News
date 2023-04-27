package com.mehdi.bbcnews.component

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse
import com.mehdi.bbcnews.domain.usecases.GetTopHeadlines
import com.mehdi.bbcnews.util.Constants.CACHED_NEWS
import com.mehdi.bbcnews.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getTopHeadlines: GetTopHeadlines,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private var cachedNews: BbcNewsResponse? = null

    private val _topHeadlines: MutableStateFlow<DataState<BbcNewsResponse>> =
        MutableStateFlow(DataState.Loading)
    val topHeadlines: StateFlow<DataState<BbcNewsResponse>> = _topHeadlines

    init {
        cachedNews = savedStateHandle.get<BbcNewsResponse>(CACHED_NEWS)
        cachedNews?.let {
            _topHeadlines.value = DataState.Success(it)
        }
    }

    fun getTopHeadlines(source: String) {
        viewModelScope.launch {
            if (cachedNews == null) {
                getTopHeadlines.call(source = source).collect { response ->
                    when (response) {
                        is DataState.Success -> {
                            _topHeadlines.value = response
                            savedStateHandle[CACHED_NEWS] = response.data
                            cachedNews = response.data
                        }

                        else -> _topHeadlines.value = response
                    }
                }
            }
        }
    }
}


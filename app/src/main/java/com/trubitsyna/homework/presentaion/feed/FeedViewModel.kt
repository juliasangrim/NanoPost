package com.trubitsyna.homework.presentaion.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.trubitsyna.homework.base.BaseViewModel
import com.trubitsyna.homework.data.local.model.Post
import com.trubitsyna.homework.data.remote.model.LoadableResult
import com.trubitsyna.homework.data.remote.model.PagedDataResponse
import com.trubitsyna.homework.domain.GetFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getFeedUseCase: GetFeedUseCase,
): ViewModel() {

    private val _mutablePostLiveData = MutableLiveData<PagingData<Post>>()
    val postLivaData: LiveData<PagingData<Post>> = _mutablePostLiveData

    fun getFeed() {
        viewModelScope.launch {
            getFeedUseCase.execute()
                .cachedIn(viewModelScope)
                .collect {
                    _mutablePostLiveData.postValue(it)
                }
        }
    }

}
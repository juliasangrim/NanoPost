package com.trubitsyna.homework.presentaion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.trubitsyna.homework.base.BaseViewModel
import com.trubitsyna.homework.data.local.model.Post
import com.trubitsyna.homework.data.remote.model.LoadableResult
import com.trubitsyna.homework.data.remote.model.Profile
import com.trubitsyna.homework.domain.GetFeedUseCase
import com.trubitsyna.homework.domain.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NanoPostViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getPostsUseCase: GetFeedUseCase
) : BaseViewModel(
) {

    private val _profileLiveData = MutableLiveData<LoadableResult<Profile>>()
    val profileLiveData: LiveData<LoadableResult<Profile>> = _profileLiveData

    private val _mutablePostLiveData = MutableLiveData<PagingData<Post>>()
    val postLiveData: LiveData<PagingData<Post>> = _mutablePostLiveData


    fun getProfile(profileId: String = "evo") {
        _profileLiveData.loadData {
            getProfileUseCase.execute(profileId)
        }
    }

    fun loadPosts() {
        viewModelScope.launch {
            getPostsUseCase.execute()
                .cachedIn(viewModelScope)
                .collect {
                    _mutablePostLiveData.postValue(it)
                }
        }
    }
}
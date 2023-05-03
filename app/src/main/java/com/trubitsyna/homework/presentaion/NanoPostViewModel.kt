package com.trubitsyna.homework.presentaion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trubitsyna.homework.data.remote.model.Profile
import com.trubitsyna.homework.domain.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.trubitsyna.homework.data.local.model.PostData
import com.trubitsyna.homework.domain.GetPostsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NanoPostViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel(
) {

    private val _profileLiveData = MutableLiveData<Profile>()
    val profileLiveData: LiveData<Profile> = _profileLiveData

    private val _mutablePostLiveData = MutableLiveData<PagingData<PostData>>()
    val postLiveData: LiveData<PagingData<PostData>> = _mutablePostLiveData
    fun getProfile(profileId: String = "evo") {
        viewModelScope.launch {
            getProfileUseCase.execute(
                profileId
            ).also { profile ->
                _profileLiveData.postValue(profile)
            }
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
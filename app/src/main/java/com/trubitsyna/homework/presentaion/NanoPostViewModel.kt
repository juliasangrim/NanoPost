package com.trubitsyna.homework.presentaion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trubitsyna.homework.data.remote.model.Profile
import com.trubitsyna.homework.domain.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NanoPostViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase
): ViewModel(
) {

    private val _profileLiveData = MutableLiveData<Profile>()
    val profileLiveData: LiveData<Profile> = _profileLiveData
    fun getProfile(profileId: String = "evo") {
        viewModelScope.launch {
            getProfileUseCase.execute(
                profileId
            ).also {
                profile ->
                _profileLiveData.postValue(profile)
            }

        }
    }
}
package com.trubitsyna.homework.presentaion.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trubitsyna.homework.data.local.model.auth.CheckNameResponse
import com.trubitsyna.homework.data.local.model.auth.UserData
import com.trubitsyna.homework.domain.auth.CheckNameUseCase
import com.trubitsyna.homework.domain.auth.LoginUseCase
import com.trubitsyna.homework.domain.auth.RegisterUseCase
import com.trubitsyna.homework.domain.auth.SaveUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val checkNameUseCase: CheckNameUseCase,
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val saveUserInfoUseCase: SaveUserInfoUseCase,
) : ViewModel() {

    private val _mutableValidationResultLiveData = MutableLiveData<CheckNameResponse>()
    val validationResultLiveData: LiveData<CheckNameResponse> = _mutableValidationResultLiveData

    private val _mutableUserLiveData = MutableLiveData<UserData>()
    val userLiveData: LiveData<UserData> = _mutableUserLiveData

    fun checkName(username: String) {
        viewModelScope.launch {
            val result = checkNameUseCase.execute(username)
            _mutableValidationResultLiveData.postValue(result)
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val result = loginUseCase.execute(username, password)
            _mutableUserLiveData.postValue(result)
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch {
            val result = registerUseCase.execute(username, password)
            _mutableUserLiveData.postValue(result)
        }
    }

    fun saveUserData(userData: UserData) {
        viewModelScope.launch {
            saveUserInfoUseCase.execute(userData)
        }
    }

}
package com.trubitsyna.homework.presentaion.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trubitsyna.homework.data.local.model.auth.CheckNameResponse
import com.trubitsyna.homework.data.local.model.auth.UserData
import com.trubitsyna.homework.domain.auth.CheckUsernameUseCase
import com.trubitsyna.homework.domain.auth.LoginUseCase
import com.trubitsyna.homework.domain.auth.RegisterUseCase
import com.trubitsyna.homework.domain.auth.SaveUserInfoUseCase
import com.trubitsyna.homework.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val checkUsernameUseCase: CheckUsernameUseCase,
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val saveUserInfoUseCase: SaveUserInfoUseCase,
) : ViewModel() {

    private val _mutableValidationResultLiveData = MutableLiveData<CheckNameResponse>()
    val validationResultLiveData: LiveData<CheckNameResponse> = _mutableValidationResultLiveData

    private val _mutableUserLiveData = MutableLiveData<UserData>()
    val userLiveData: LiveData<UserData> = _mutableUserLiveData

    fun checkUsername(username: String) {
        viewModelScope.launch {
            val result = checkUsernameUseCase.execute(username)
            _mutableValidationResultLiveData.postValue(result)
        }
    }

    fun isPasswordValid(password: String) =
        password.length >= Constants.VALID_LENGTH_PASSWORD

    fun auth(username: String, password: String) {
        when (_mutableValidationResultLiveData.value) {
            CheckNameResponse.TAKEN -> {
                login(username, password)
            }

            CheckNameResponse.FREE -> {
                register(username, password)
            }

            else -> {/* no-op */}
        }
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch {
            val result = loginUseCase.execute(username, password)
            _mutableUserLiveData.postValue(result)
        }
    }

    private fun register(username: String, password: String) {
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
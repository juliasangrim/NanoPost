package com.trubitsyna.homework.domain.auth

import com.trubitsyna.homework.data.local.model.auth.CheckNameResponse
import com.trubitsyna.homework.data.remote.model.auth.ApiValidateUsernameResponse
import com.trubitsyna.homework.data.repository.AuthRepositoryImpl
import javax.inject.Inject

class CheckNameUseCase @Inject constructor(
    private val authRepository: AuthRepositoryImpl
) {
    suspend fun execute(username: String): CheckNameResponse {
        return authRepository.checkUsername(username)
    }
}
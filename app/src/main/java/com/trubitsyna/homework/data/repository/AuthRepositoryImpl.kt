package com.trubitsyna.homework.data.repository

import com.trubitsyna.homework.data.local.model.auth.CheckNameResponse
import com.trubitsyna.homework.data.local.model.auth.UserData
import com.trubitsyna.homework.data.mapper.CheckNameMapper
import com.trubitsyna.homework.data.mapper.TokenMapper
import com.trubitsyna.homework.data.remote.NanopostAuthApiService
import com.trubitsyna.homework.data.remote.model.auth.ApiRegistrationRequest
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val authApiService: NanopostAuthApiService,
    private val checkNameMapper: CheckNameMapper,
    private val tokenMapper: TokenMapper,
) {

    suspend fun checkUsername(username: String): CheckNameResponse {
        return checkNameMapper.apiValidateResponseToCheckNameResponse(
            authApiService.checkUsername(username)
        )
    }

    suspend fun register(username: String, password: String): UserData {
        val response = authApiService.register(
            ApiRegistrationRequest(
                username = username,
                password = password
            )
        )
        return tokenMapper.apiTokenResponseToUserData(response)
    }

    suspend fun login(username: String, password: String): UserData {
        val response = authApiService.login(
            username = username,
            password = password
        )
        return tokenMapper.apiTokenResponseToUserData(response)
    }
}
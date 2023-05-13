package com.trubitsyna.homework.domain.auth

import com.trubitsyna.homework.data.local.model.auth.UserData
import com.trubitsyna.homework.data.repository.DataStoreRepositoryImpl
import javax.inject.Inject

class SaveUserInfoUseCase @Inject constructor(
    private val dataStoreRepositoryImpl: DataStoreRepositoryImpl,
){
    suspend fun execute(userData: UserData) {
        dataStoreRepositoryImpl.saveUserData(userData)
    }
}
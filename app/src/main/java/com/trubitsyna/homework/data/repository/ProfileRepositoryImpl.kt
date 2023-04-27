package com.trubitsyna.homework.data.repository

import com.trubitsyna.homework.data.mapper.ProfileMapper
import com.trubitsyna.homework.data.remote.NanoPostApiService
import com.trubitsyna.homework.data.remote.model.Profile
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiService: NanoPostApiService,
    private val profileMapper: ProfileMapper
) : ProfileRepository {
    override suspend fun getProfile(profileId: String): Profile {
        return profileMapper.apiToModel(apiService.getProfile(profileId))
    }
}
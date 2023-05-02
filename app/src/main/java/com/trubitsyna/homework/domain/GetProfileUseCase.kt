package com.trubitsyna.homework.domain

import com.trubitsyna.homework.data.remote.model.Profile
import com.trubitsyna.homework.data.repository.ProfileRepository
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend fun execute(profileId: String): Profile {
        return repository.getProfile(profileId)
    }
}
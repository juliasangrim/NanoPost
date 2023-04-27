package com.trubitsyna.homework.data.mapper

import com.trubitsyna.homework.data.remote.model.ApiProfile
import com.trubitsyna.homework.data.remote.model.Profile
import javax.inject.Inject

class ProfileMapper @Inject constructor() {
    fun apiToModel(apiModel: ApiProfile) : Profile = Profile(
        id = apiModel.id,
        username = apiModel.username,
        displayName = apiModel.displayName,
        bio = apiModel.bio,
        avatarId = apiModel.avatarId,
        avatarLarge = apiModel.avatarLarge,
        subscribed = apiModel.subscribed,
        subscribersCount = apiModel.subscribersCount,
        postsCount = apiModel.postsCount,
        imagesCount = apiModel.imagesCount
    )
}
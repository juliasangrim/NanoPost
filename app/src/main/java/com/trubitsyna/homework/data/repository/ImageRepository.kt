package com.trubitsyna.homework.data.repository

import androidx.paging.PagingData
import com.trubitsyna.homework.data.local.model.Image
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun getProfileImages(
        profileId: String,
        loadExceptionCallback: () -> Unit
    ): Flow<PagingData<Image>>

    suspend fun getImage(imageId: String): Image
    suspend fun deleteImage(imageId: String)
}
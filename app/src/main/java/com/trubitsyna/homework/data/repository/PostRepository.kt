package com.trubitsyna.homework.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.trubitsyna.homework.data.local.model.Post
import com.trubitsyna.homework.data.mapper.PostMapper
import com.trubitsyna.homework.data.paging.FeedPagingSource
import com.trubitsyna.homework.data.remote.NanoPostApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val nanoPostApiService: NanoPostApiService,
    private val mapper: PostMapper
) {
    fun getFeed(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(30, enablePlaceholders = false),
            pagingSourceFactory = { FeedPagingSource(nanoPostApiService) }
        ).flow.map { value ->
            value.map {
                mapper.apiPostToPostData(it)
            }
        }
    }

    suspend fun createPost(text: String?, list: List<ByteArray>?): Post {
        val image = "image/*"
        var image0: MultipartBody.Part? = null
        list?.getOrNull(0)?.let {
            image0 = MultipartBody.Part.createFormData(
                "image1",
                "image1.jpg",
                it.toRequestBody(image.toMediaType())
            )
        }
        var image1: MultipartBody.Part? = null
        list?.getOrNull(0)?.let {
            image1 = MultipartBody.Part.createFormData(
                "image2",
                "image2.jpg",
                it.toRequestBody(image.toMediaType())
            )
        }
        var image2: MultipartBody.Part? = null
        list?.getOrNull(0)?.let {
            image2 = MultipartBody.Part.createFormData(
                "image2",
                "image2.jpg",
                it.toRequestBody(image.toMediaType())
            )
        }
        var image3: MultipartBody.Part? = null
        list?.getOrNull(0)?.let {
            image3 = MultipartBody.Part.createFormData(
                "image3",
                "image3.jpg",
                it.toRequestBody(image.toMediaType())
            )
        }
        return mapper.apiPostToPostData(
            nanoPostApiService.createPost(
                text?.toRequestBody(),
                image0,
                image1,
                image2,
                image3
            )
        )
    }
}
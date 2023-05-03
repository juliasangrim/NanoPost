package com.trubitsyna.homework.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.trubitsyna.homework.data.local.model.PostData
import com.trubitsyna.homework.data.mapper.PostMapper
import com.trubitsyna.homework.data.paging.PostPagingSource
import com.trubitsyna.homework.data.remote.NanoPostApiService
import com.trubitsyna.homework.data.remote.model.ApiPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val nanoPostApiService: NanoPostApiService,
    private val mapper: PostMapper
) {
    fun getProfilePost(): Flow<PagingData<PostData>> {
        return Pager(
            config = PagingConfig(30, enablePlaceholders = false),
            pagingSourceFactory = { PostPagingSource(nanoPostApiService) }
        ).flow.map { value ->
            value.map {
                mapper.apiPostToPostData(it)
            }
        }
    }
}
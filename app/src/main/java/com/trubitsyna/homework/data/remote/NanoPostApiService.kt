package com.trubitsyna.homework.data.remote

import com.trubitsyna.homework.data.remote.model.ApiPost
import com.trubitsyna.homework.data.remote.model.ApiProfile
import com.trubitsyna.homework.data.remote.model.PagedDataResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NanoPostApiService {
    @GET("api/v1/profile/{profileId}")
    suspend fun getProfile(
        @Path("profileId") profileId: String
    ): ApiProfile

    @GET("/api/v1/posts/{profileId}")
    suspend fun getPosts(
        @Path("profileId") profileId: String,
        @Query("count") count: Int,
        @Query("offset") offset: String?
    ): PagedDataResponse<ApiPost>

}
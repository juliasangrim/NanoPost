package com.trubitsyna.homework.data.mapper

import com.trubitsyna.homework.data.local.model.PostData
import com.trubitsyna.homework.data.remote.model.ApiPost
import java.sql.Date
import javax.inject.Inject

class PostMapper @Inject constructor() {
    fun apiPostToPostData(apiPost: ApiPost): PostData {
        return PostData(
            id = apiPost.id,
            name = "evo",
            date = Date(apiPost.dateCreated),
            mainText = apiPost.text.toString(),
            imageUrl = "https://i.pinimg.com/736x/23/86/e3/2386e3023848e6754b8f0ad9597676a7.jpg",
            likeCount = 0
        )
    }

}
package com.trubitsyna.homework.data

import java.sql.Date
import java.util.UUID

data class PostData(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val date: Date,
    val imageUrl: String,
    val mainText: String,
    val likeCount: Int
)
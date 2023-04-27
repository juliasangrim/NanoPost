package com.trubitsyna.homework.presentaion.list.data

import java.util.UUID

data class ImageData(
    val id: String = UUID.randomUUID().toString(),
    val imageUrl: String
)
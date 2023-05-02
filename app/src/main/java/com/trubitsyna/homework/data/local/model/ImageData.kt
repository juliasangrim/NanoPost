package com.trubitsyna.homework.data.local.model

import java.util.UUID

data class ImageData(
    val id: String = UUID.randomUUID().toString(),
    val imageUrl: String
)
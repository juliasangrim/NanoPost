package com.trubitsyna.homework.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiImage(
    val id: String,
    val owner: ApiProfileCompact,
    val dateCreated: Long,
    val sizes: List<ApiImageSize>,
)
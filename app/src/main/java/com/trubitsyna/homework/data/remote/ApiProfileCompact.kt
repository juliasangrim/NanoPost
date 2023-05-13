package com.trubitsyna.homework.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ApiProfileCompact (
    val id: String,
    val username: String,
    val displayName: String?,
    val avatarUrl: String?,
    val subscribed: Boolean
)
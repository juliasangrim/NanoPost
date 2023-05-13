package com.trubitsyna.homework.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ApiImage(
    val id: String,
    val dateCreated: String,

)

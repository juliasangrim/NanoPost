package com.trubitsyna.homework.data.local.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class ImageCardData(
    val id: String = UUID.randomUUID().toString(),
    val listImagesUrl: List<String>
) : Parcelable
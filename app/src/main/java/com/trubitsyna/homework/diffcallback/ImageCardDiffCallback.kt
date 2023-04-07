package com.trubitsyna.homework.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.trubitsyna.homework.data.ImageCardData

class ImageCardDiffCallback: DiffUtil.ItemCallback<ImageCardData>() {
    override fun areItemsTheSame(oldItem: ImageCardData, newItem: ImageCardData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageCardData, newItem: ImageCardData): Boolean {
        return oldItem == newItem
    }
}
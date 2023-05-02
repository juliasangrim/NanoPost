package com.trubitsyna.homework.presentaion.image_list.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.trubitsyna.homework.data.local.model.ImageData

class ImageDiffCallback : DiffUtil.ItemCallback<ImageData>() {
    override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
        return oldItem == newItem
    }
}
package com.trubitsyna.homework.diffcallback

import androidx.recyclerview.widget.DiffUtil
import com.trubitsyna.homework.data.ProfileData

class ProfileDiffCallback: DiffUtil.ItemCallback<ProfileData>() {
    override fun areItemsTheSame(oldItem: ProfileData, newItem: ProfileData): Boolean {
        return oldItem.id != newItem.id
    }

    override fun areContentsTheSame(oldItem: ProfileData, newItem: ProfileData): Boolean {
        return oldItem == newItem
    }
}
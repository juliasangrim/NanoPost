package com.trubitsyna.homework.presentaion.add_post

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.trubitsyna.homework.data.local.model.ImageData
import com.trubitsyna.homework.databinding.ViewImageItemWithDeleteBinding
import com.trubitsyna.homework.presentaion.image_list.diffcallback.ImageDiffCallback
import javax.inject.Inject

class ImageAddPostViewAdapter @Inject constructor() :
    ListAdapter<ImageData, ImageAddPostViewAdapter.ImageViewHolder>(
        ImageDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        Log.i("LIST", "CREATING")
        return ImageViewHolder(
            ViewImageItemWithDeleteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImageViewHolder(
        private val binding: ViewImageItemWithDeleteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImageData) {
            with(binding) {
                imageViewAddImage.load(item.imageUrl)
            }
        }
    }
}
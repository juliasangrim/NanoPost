package com.trubitsyna.homework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.trubitsyna.homework.data.ImageCardData
import com.trubitsyna.homework.databinding.ViewCardImagesBinding
import com.trubitsyna.homework.diffcallback.ImageCardDiffCallback

class ImageCardAdapter : ListAdapter<ImageCardData, ImageCardAdapter.ImageViewHolder>(
    ImageCardDiffCallback()
) {

    private lateinit var onClick: (List<String>) -> Unit

    fun setCallback(callback: (List<String>) -> Unit) {
        onClick = callback
    }

    inner class ImageViewHolder(
        private val binding: ViewCardImagesBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: ImageCardData) {
            with(binding) {
                imageViewFirst.load(item.listImagesUrl[0])
                imageViewSecond.load(item.listImagesUrl[1])
                imageViewThird.load(item.listImagesUrl[2])
                imageViewFourth.load(item.listImagesUrl[3])
                root.setOnClickListener {
                    onClick(item.listImagesUrl)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ViewCardImagesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
package com.trubitsyna.homework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.trubitsyna.homework.data.PostData
import com.trubitsyna.homework.data.PostIntentData
import com.trubitsyna.homework.databinding.ViewCardPostBinding
import com.trubitsyna.homework.diffcallback.PostDiffCallback
import com.trubitsyna.homework.utils.Constants
import java.text.SimpleDateFormat

class PostAdapter : ListAdapter<PostData, PostAdapter.PostViewHolder>(
    PostDiffCallback()
) {

    private lateinit var onClick: (PostIntentData) -> Unit

    fun setCallback(callback: (PostIntentData) -> Unit) {
        onClick = callback
    }

    inner class PostViewHolder(
        private val binding: ViewCardPostBinding
    ) : ViewHolder(binding.root) {

        fun bind(item: PostData) {
            with(binding.layoutViewPost) {
                textViewName.text = item.name
                val dateFormatter = SimpleDateFormat(Constants.DATE_PATTERN)
                textViewData.text = dateFormatter.format(item.date)
                textViewPost.text = item.mainText
                imageViewPost.load(item.imageUrl)
                buttonLike.text = item.likeCount.toString()
                root.setOnClickListener {
                    onClick.invoke(converter(item))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ViewCardPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun converter(item: PostData) =
        PostIntentData(
            item.id,
            item.name,
            item.date.toString(),
            item.imageUrl,
            item.mainText,
            item.likeCount.toString()
        )
}
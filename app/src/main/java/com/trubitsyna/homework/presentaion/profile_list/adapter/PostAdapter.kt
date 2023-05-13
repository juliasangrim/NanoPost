package com.trubitsyna.homework.presentaion.profile_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.trubitsyna.homework.data.local.model.Post
import com.trubitsyna.homework.databinding.ViewCardPostBinding
import com.trubitsyna.homework.presentaion.profile_list.diffcallback.PostDiffCallback
import com.trubitsyna.homework.utils.Constants
import java.text.SimpleDateFormat
import javax.inject.Inject

class PostAdapter @Inject constructor() :
    PagingDataAdapter<Post, PostAdapter.PostViewHolder>(
        PostDiffCallback()
    ) {

    private lateinit var onClick: (Post) -> Unit

    fun setCallback(callback: (Post) -> Unit) {
        onClick = callback
    }

    inner class PostViewHolder(
        private val binding: ViewCardPostBinding
    ) : ViewHolder(binding.root) {

        fun bind(item: Post) {
            with(binding.layoutViewPost) {
                textViewName.text = item.name
                val dateFormatter = SimpleDateFormat(Constants.DATE_PATTERN)
                textViewDate.text = dateFormatter.format(item.date)
                textViewPost.text = item.mainText
                imageViewPost.load(item.imageUrl)
                buttonLike.text = item.likeCount.toString()
                root.setOnClickListener {
                    onClick.invoke(item)
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
        getItem(position)?.let { holder.bind(it) }
    }
}
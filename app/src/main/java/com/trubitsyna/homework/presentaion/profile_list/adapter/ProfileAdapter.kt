package com.trubitsyna.homework.presentaion.profile_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.trubitsyna.homework.data.local.model.ProfileData
import com.trubitsyna.homework.databinding.ViewCardProfileBinding
import javax.inject.Inject

class ProfileAdapter @Inject constructor() :
    Adapter<ProfileAdapter.ProfileViewHolder>() {

    companion object {
        private const val MAX_ITEM_COUNT = 1
    }

    private var profileData: ProfileData? = null

    fun setItem(item: ProfileData) {
        profileData = item
        notifyDataSetChanged()
    }
    inner class ProfileViewHolder(
        private var binding: ViewCardProfileBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: ProfileData?) {
            with(binding) {
                textViewName.text = item?.name
                textViewProfileSubtext.text = item?.subtext
                textViewImageCounter.text = item?.imageCount.toString()
                textViewSubscribeCounter.text = item?.subscribeCount.toString()
                textViewPostCounter.text = item?.postCount.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ViewCardProfileBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProfileViewHolder(binding)
    }

    override fun getItemCount(): Int = if (profileData != null) MAX_ITEM_COUNT else 0

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(profileData)
    }
}
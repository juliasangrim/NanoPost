package com.trubitsyna.homework.presentaion.post

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.trubitsyna.homework.R
import com.trubitsyna.homework.databinding.FragmentPostBinding
import com.trubitsyna.homework.presentaion.profile_list.adapter.PostAdapter
import com.trubitsyna.homework.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class PostFragment : Fragment(R.layout.fragment_post) {

    @Inject
    lateinit var postAdapter: PostAdapter

    private val binding: FragmentPostBinding by viewBinding(FragmentPostBinding::bind)
//    private val args by navArgs<PostFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val data = args.postData
//        with(binding) {
//            with(layoutViewCardPost) {
//                textViewName.text = data?.name
//                val dateFormatter = SimpleDateFormat(Constants.DATE_PATTERN)
//                textViewDate.text = data?.date?.let { dateFormatter.format(it) }
//                textViewPost.text = data?.mainText
//                imageViewPost.load(data?.imageUrl)
//                buttonLike.text = data?.likeCount.toString()
//            }
//            toolbarPost.setNavigationOnClickListener {
//                findNavController().popBackStack()
//            }
//        }
    }
}
package com.trubitsyna.homework.presentaion.profile_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.trubitsyna.homework.R
import com.trubitsyna.homework.databinding.FragmentProfileBinding
import com.trubitsyna.homework.presentaion.NanoPostViewModel
import com.trubitsyna.homework.presentaion.profile_list.adapter.ImageCardAdapter
import com.trubitsyna.homework.presentaion.profile_list.adapter.PostAdapter
import com.trubitsyna.homework.presentaion.profile_list.adapter.ProfileAdapter
import com.trubitsyna.homework.data.local.model.ImageCardData
import com.trubitsyna.homework.data.local.model.PostData
import com.trubitsyna.homework.data.local.model.ProfileData
import com.trubitsyna.homework.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Date
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel by viewModels<NanoPostViewModel>()

    @Inject
    lateinit var profileAdapter: ProfileAdapter

    @Inject
    lateinit var imageCardAdapter: ImageCardAdapter

    @Inject
    lateinit var postAdapter: PostAdapter

    lateinit var imageCard: ImageCardData
    lateinit var postList: List<PostData>

    init {
        val mockImagesUrlList = listOf(
            Constants.IMAGE1_URL,
            Constants.IMAGE2_URL,
            Constants.IMAGE3_URL,
            Constants.IMAGE4_URL,
            Constants.IMAGE1_URL
        )

        imageCard = ImageCardData(listImagesUrl = mockImagesUrlList)

        postList = listOf<PostData>(
            PostData(
                name = Constants.NAME,
                date = Date(System.currentTimeMillis()),
                imageUrl = Constants.IMAGE1_URL,
                mainText = Constants.MAIN_TEXT,
                likeCount = Constants.LIKE_COUNT
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProfile()
        Log.i("D", "log")
        imageCardAdapter.setItem(imageCard)
        postAdapter.submitList(postList)
        with(imageCardAdapter) {
            setCallback {
                if (it != null) {
                    findNavController().navigate(
                        ProfileFragmentDirections.actionProfileFragmentToImageFragment(
                            it.toTypedArray()
                        )
                    )
                }
            }
        }
        with(postAdapter) {
            setCallback {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToPostFragment(it)
                )
            }
        }

        val concatenatedAdapter = ConcatAdapter(profileAdapter, imageCardAdapter, postAdapter)
        with(binding) {
            recyclerViewConcat.adapter = concatenatedAdapter
        }

        viewModel.profileLiveData.observe(viewLifecycleOwner) {
            profileAdapter.setItem(
                ProfileData(
                    name = it.username,
                    subtext = it.bio ?: "",
                    imageCount = it.imagesCount,
                    postCount = it.postsCount,
                    subscribeCount = it.subscribersCount
                )
            )
        }

    }
}
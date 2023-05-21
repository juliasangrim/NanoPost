package com.trubitsyna.homework.presentaion.image_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.trubitsyna.homework.R
import com.trubitsyna.homework.databinding.FragmentImageBinding
import com.trubitsyna.homework.presentaion.adapter.PagingImageViewAdapter
import com.trubitsyna.homework.utils.replace
import com.trubitsyna.homework.utils.showErrorWithAction
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImageListFragment : Fragment(R.layout.fragment_image) {

    private val binding: FragmentImageBinding by viewBinding(FragmentImageBinding::bind)
    private val viewModel by viewModels<ImageListViewModel>()

    @Inject
    lateinit var imageViewAdapter: PagingImageViewAdapter

    companion object {
        private const val COLUMN_NUM = 3
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            with(toolBarImages) {
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
                recyclerViewImages.apply {
                    layoutManager = GridLayoutManager(
                        requireContext(),
                        COLUMN_NUM,
                        GridLayoutManager.VERTICAL,
                        false
                    )
                    imageViewAdapter.setCallback {
                        findNavController().navigate(
                            ImageListFragmentDirections.actionImageListFragmentToImageFragment(it.id)
                        )
                    }
                    adapter = imageViewAdapter
                }
            }
            recyclerViewImages.replace(progressBarImages.root)
            viewModel.getUserId()

            viewModel.userIdLiveData.observe(viewLifecycleOwner) {
                it?.let { profileId ->
                    viewModel.loadImages(profileId) {
                        binding.root.showErrorWithAction(R.string.error_msg_network) {
                            viewModel.getUserId()
                        }
                    }
                }
            }
            viewModel.imageListLiveData.observe(viewLifecycleOwner) {
                imageViewAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                progressBarImages.root.replace(recyclerViewImages)
            }
        }
    }

}
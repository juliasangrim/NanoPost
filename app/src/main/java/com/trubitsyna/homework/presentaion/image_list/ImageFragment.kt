package com.trubitsyna.homework.presentaion.image_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.trubitsyna.homework.R
import com.trubitsyna.homework.data.local.model.ImageData
import com.trubitsyna.homework.presentaion.image_list.adapter.ImageViewAdapter
import com.trubitsyna.homework.databinding.FragmentImageBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImageFragment : Fragment(R.layout.fragment_image) {

    private val binding: FragmentImageBinding by viewBinding(FragmentImageBinding::bind)
//    private val args by navArgs<ImageFragmentArgs>()

    @Inject
    lateinit var imageViewAdapter: ImageViewAdapter

    companion object {
        private const val COLUMN_NUM = 3
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val listImageUrl = args.imageInfo?.map {
//            ImageData(
//                imageUrl = it
//            )
//        }
//        imageViewAdapter.submitList(listImageUrl)
//
//        with(binding) {
//            recyclerViewImages.adapter = imageViewAdapter
//            recyclerViewImages.layoutManager = GridLayoutManager(context, COLUMN_NUM)
//            toolBarImages.setNavigationOnClickListener {
//                findNavController().popBackStack()
//            }
//        }

    }
}
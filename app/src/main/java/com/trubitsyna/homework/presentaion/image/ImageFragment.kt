package com.trubitsyna.homework.presentaion.image

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.trubitsyna.homework.R
import com.trubitsyna.homework.data.remote.model.LoadableResult
import com.trubitsyna.homework.databinding.FragmentImageItemViewBinding
import com.trubitsyna.homework.utils.getMaxSizeImageUrl
import com.trubitsyna.homework.utils.replace
import com.trubitsyna.homework.utils.showErrorWithAction
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class ImageFragment : Fragment(R.layout.fragment_image_item_view) {
    private val binding by viewBinding(FragmentImageItemViewBinding::bind)
    private val viewModel by viewModels<ImageViewModel>()
    private val args by navArgs<ImageFragmentArgs>()

    @Inject
    lateinit var dateFormatter: SimpleDateFormat

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarImage.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        viewModel.getImage(args.imageId)

        viewModel.imageLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is LoadableResult.Loading -> {
                    binding.imageViewImage.replace(binding.progressBarImage.root)
                }

                is LoadableResult.Error -> {
                    binding.root.showErrorWithAction(R.string.error_msg_network) {
                        viewModel.getImage(args.imageId)
                    }
                }

                is LoadableResult.Success -> {
                    with(binding) {
                        result.data.also { image ->
                            textViewName.text = image.owner.displayName ?: image.owner.username
                            textViewDate.text = dateFormatter.format(image.dateCreated)
                            imageViewProfile.load(image.owner.avatarUrl)
                            imageViewImage.load(image.getMaxSizeImageUrl())
                        }
                        progressBarImage.root.replace(imageViewImage)
                    }
                }
            }

        }

    }
}
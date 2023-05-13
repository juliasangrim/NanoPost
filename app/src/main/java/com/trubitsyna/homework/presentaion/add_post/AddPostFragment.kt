package com.trubitsyna.homework.presentaion.add_post

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.trubitsyna.homework.R
import com.trubitsyna.homework.data.local.model.ImageData
import com.trubitsyna.homework.databinding.FragmentAddPostBinding
import com.trubitsyna.homework.service.CreatePostService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddPostFragment: Fragment(R.layout.fragment_add_post) {
    private val binding by viewBinding(FragmentAddPostBinding::bind)

    @Inject
    lateinit var imageAddPostViewAdapter: ImageAddPostViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var listUris: List<Uri>? = null
        val pickImage =
            registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(4)) { uris ->
                if (uris.isNotEmpty()) {
                    startActivity(CreatePostService.newIntent(requireContext(),"text", uris))
                    val listImageData = uris.map { uri -> ImageData(imageUrl = uri.toString())}
                    imageAddPostViewAdapter.submitList(listImageData)

                }
            }

        with(binding) {
            recyclerViewImages.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = imageAddPostViewAdapter
            }
            chipAddImage.setOnClickListener {
                pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

        }

    }
}
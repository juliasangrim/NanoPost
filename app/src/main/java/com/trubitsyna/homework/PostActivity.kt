package com.trubitsyna.homework

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.trubitsyna.homework.data.PostIntentData
import com.trubitsyna.homework.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {

    companion object {
        private const val POST_ARG_KEY = "POST_ARG_KEY"

        fun createIntent(context: Context, data: PostIntentData) =
            Intent(context, PostActivity::class.java).apply {
                putExtra(POST_ARG_KEY, data)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.extras?.getParcelable(POST_ARG_KEY) as PostIntentData?

        with(binding) {
            with(layoutViewCardPost) {
                textViewName.text = data?.name
                textViewData.text = data?.date
                textViewPost.text = data?.mainText
                imageViewPost.load(data?.imageUrl)
                buttonLike.text = data?.likeCount
            }
        }
    }
}
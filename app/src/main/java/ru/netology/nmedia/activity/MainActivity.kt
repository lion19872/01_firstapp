package ru.netology.nmedia.dto.ru.netology.nmedia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likeCount.text = formatCount(post.likes)
                sharesNumber.text = formatCount(post.shares)
                like.setImageResource(if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24)
                views.text = formatCount(post.views)

            }
        }

        binding.root.setOnClickListener {
            Log.d("stuff", "stuff")
        }

        binding.avatar.setOnClickListener {
            Log.d("stuff", "avatar")
        }

        binding.like.setOnClickListener {
            Log.d("stuff", "like")
            viewModel.like()

        }

        binding.share.setOnClickListener {
            Log.d("stuff", "share")
            viewModel.share()

        }

    }

    private fun formatCount(count: Int): String {
        return when {
            count < 1000 -> count.toString()
            count < 10_000 -> "${count / 1000}K"
            count < 1_000_000 -> "${count / 1000}.${count % 1000}K"
            else -> "${count / 1_000_000}M"
        }
    }
}

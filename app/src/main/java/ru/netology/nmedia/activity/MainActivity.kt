package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(
            onLikeListener = { post ->
                viewModel.likeById(post.id)
            },
            onShareListener = { post ->
                viewModel.shareById(post.id)
            }
        )
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
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


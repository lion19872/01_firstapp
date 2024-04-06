package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

typealias onLikeListener = (Post) -> Unit
typealias onShareListener = (Post) -> Unit
typealias onRemoveListener = (Post) -> Unit

class PostsAdapter(
    private val onLike: onLikeListener,
    private val onShare: onShareListener,
    private val onRemove: onRemoveListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(view, onLike, onShare, onRemove)

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLike: onLikeListener,
    private val onShare: onShareListener,
    private val onRemove: onRemoveListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            )
            likes.text = post.likes.toString()
            sharesNumber.text = post.shares.toString()
            views.text = post.views.toString()
            like.setOnClickListener {
                onLike(post)
            }
            share.setOnClickListener {
                onShare(post)
            }
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onRemove(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }
        }
    }

}

object PostDiffCallBack : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem

}
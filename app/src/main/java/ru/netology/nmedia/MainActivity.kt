package ru.netology.nmedia
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {

    private var sharesCount = 0
    private var likesCount = 999

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likeCount.text = formatCount(likesCount)
            sharesNumber.text = formatCount(sharesCount)

            root.setOnClickListener {
                Log.d("stuff", "stuff")
            }

            avatar.setOnClickListener {
                Log.d("stuff", "avatar")
            }

            like?.setOnClickListener {
                Log.d("stuff", "like")
                post.likedByMe = !post.likedByMe
                if (post.likedByMe) {
                    likesCount++
                } else {
                    likesCount--
                }
                likeCount.text = formatCount(likesCount)
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
            }

            share?.setOnClickListener {
                Log.d("stuff", "share")
                sharesCount += 100
                sharesNumber.text = formatCount(sharesCount)
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
}

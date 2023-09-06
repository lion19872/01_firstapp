package ru.netology.nmedia
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post



class MainActivity : AppCompatActivity() {

    private var sharesCount = 0
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


        with(binding){
            author.text = post.author
            published.text= post.published
            content.text= post.content
            if(post.likedByMe){
                like?.setImageResource(R.drawable.ic_liked_24)
            }
            likeCount?.text = post.likes.toString()

            share?.setOnClickListener {
                Log.d("stuff", "share")
                sharesCount = sharesCount +10
                sharesNumber.text = sharesCount.toString()
            }

             binding.root.setOnClickListener {
               Log.d("stuff", "stuff")
              }

            binding.like?.setOnClickListener {
                Log.d("stuff", "like")
                post.likedByMe = !post.likedByMe
                binding.like?.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                binding.likeCount?.text = post.likes.toString()
            }
            binding.avatar.setOnClickListener {
                Log.d("stuff", "avatar")
            }
        }
    }
}
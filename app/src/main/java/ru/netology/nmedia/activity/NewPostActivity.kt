package ru.netology.nmedia.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ok.setOnClickListener{
            val text = binding.edit.text.toString()
            if (text.isNotBlank()){
                setResult(RESULT_OK, Intent().apply { putExtra(KEY_TEXT, text) })
            } else {
                setResult(RESULT_CANCELED)
            }
            finish()
        }

    }
    companion object{
        const val KEY_TEXT = "post_text"
    }
}

object NewPostContract : ActivityResultContract<Unit, String?>(){
    override fun createIntent(context: Context, input: Unit) = Intent(context, NewPostActivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?) = intent?.getStringExtra(NewPostActivity.KEY_TEXT)

}
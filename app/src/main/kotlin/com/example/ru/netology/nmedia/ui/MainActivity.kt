package com.example.ru.netology.nmedia.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ru.netology.nmedia.R
import com.example.ru.netology.nmedia.data.impl.PostInteractionListener
import com.example.ru.netology.nmedia.data.impl.PostsAdapter
import com.example.ru.netology.nmedia.databinding.ActivityMainBinding
import com.example.ru.netology.nmedia.util.AndroidUtils
import com.example.ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(
            object : PostInteractionListener {
                override fun onEdit(post: Post) {
                    viewModel.edit(post)
                }

                override fun onLike(post: Post) {
                    viewModel.onLikeClicked(post.id.toLong())
                }

                override fun onRepost(post: Post) {
                    viewModel.onRepostClicked(post.id.toLong())
                }

                override fun onRemove(post: Post) {
                    viewModel.onRemoveClicked(post.id.toLong())
                }

            }
        )
        binding.save.setOnClickListener {

            with(binding.content) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(context, context.getString(R.string.empty_text_error), Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                viewModel.changeContent(text.toString().trim())
                viewModel.save()

                clearFocus()
                setText("")
                AndroidUtils.hideKeyboard(this)
            }
        }
        binding.List.adapter = adapter
        viewModel.edited.observe(this) {
            if (it.id == 0) {
                return@observe
            }

            with(binding.content) {
                setText(it.text)
                AndroidUtils.showKeyboard(this)
                requestFocus()
            }
        }
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)

        }
    }


}


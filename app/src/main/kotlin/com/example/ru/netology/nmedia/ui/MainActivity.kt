package com.example.ru.netology.nmedia.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ru.netology.nmedia.data.impl.PostsAdapter
import com.example.ru.netology.nmedia.databinding.ActivityMainBinding
import com.example.ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(
            {
            viewModel.onLikeClicked(it.id.toLong()) },
        {
            viewModel.onRepostClicked(it.id.toLong())}
        )

        binding.List.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)

        }
    }



}


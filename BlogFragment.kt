package com.akgroup76.akmax.ui.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akgroup76.akmax.databinding.FragmentBlogBinding
import com.akgroup76.akmax.model.Book
import com.akgroup76.akmax.model.Article

class BlogFragment : Fragment() {

    private lateinit var binding: FragmentBlogBinding
    private val bookList = listOf<Book>() // Replace with Firebase or mock data
    private val articleList = listOf<Article>() // Replace with Firebase or mock data

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlogBinding.inflate(inflater, container, false)

        binding.recyclerBooks.layoutManager = LinearLayoutManager(context)
        binding.recyclerArticles.layoutManager = LinearLayoutManager(context)

        binding.recyclerBooks.adapter = BookAdapter(bookList)
        binding.recyclerArticles.adapter = ArticleAdapter(articleList)

        return binding.root
    }
}

package com.akgroup76.akmax.ui.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akgroup76.akmax.databinding.FragmentArticleDetailBinding
import com.akgroup76.akmax.model.Article

class ArticleDetailFragment(private val article: Article) : Fragment() {

    private lateinit var binding: FragmentArticleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false)

        binding.tvTitle.text = article.title
        binding.tvAuthor.text = article.author
        binding.tvContent.text = article.content
        binding.tvPrice.text = "₹${article.price}"
        // Load image with Glide/Coil

        binding.btnBuy.setOnClickListener {
            // Implement payment or content unlock logic
        }

        return binding.root
    }
}

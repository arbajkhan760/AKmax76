package com.akgroup76.akmax.ui.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akgroup76.akmax.databinding.FragmentUploadArticleBinding
import com.akgroup76.akmax.model.Article

class UploadArticleFragment : Fragment() {

    private lateinit var binding: FragmentUploadArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUploadArticleBinding.inflate(inflater, container, false)

        binding.btnUpload.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()
            val author = binding.etAuthor.text.toString()
            val price = binding.etPrice.text.toString().toDoubleOrNull() ?: 0.0

            val article = Article(
                title = title,
                content = content,
                author = author,
                price = price
            )

            // Upload article to Firebase
        }

        return binding.root
    }
}

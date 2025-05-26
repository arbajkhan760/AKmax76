package com.akgroup76.akmax.ui.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akgroup76.akmax.databinding.FragmentBookDetailBinding
import com.akgroup76.akmax.model.Book

class BookDetailFragment(private val book: Book) : Fragment() {

    private lateinit var binding: FragmentBookDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookDetailBinding.inflate(inflater, container, false)

        binding.tvTitle.text = book.title
        binding.tvAuthor.text = book.author
        binding.tvDescription.text = book.description
        binding.tvPrice.text = "₹${book.price}"
        // Load cover image with Glide or Coil

        binding.btnBuy.setOnClickListener {
            // Implement payment or download logic here
        }

        return binding.root
    }
}

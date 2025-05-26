package com.akgroup76.akmax.ui.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akgroup76.akmax.databinding.FragmentUploadBookBinding
import com.akgroup76.akmax.model.Book

class UploadBookFragment : Fragment() {

    private lateinit var binding: FragmentUploadBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUploadBookBinding.inflate(inflater, container, false)

        binding.btnUpload.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val author = binding.etAuthor.text.toString()
            val desc = binding.etDescription.text.toString()
            val price = binding.etPrice.text.toString().toDoubleOrNull() ?: 0.0

            val book = Book(
                title = title,
                author = author,
                description = desc,
                price = price
            )

            // Upload book to Firebase
        }

        return binding.root
    }
}

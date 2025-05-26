package com.akgroup76.akmax.ui.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.akgroup76.akmax.R
import com.akgroup76.akmax.model.Article
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UploadArticleFragment : Fragment() {

    private lateinit var titleInput: EditText
    private lateinit var contentInput: EditText
    private lateinit var authorInput: EditText
    private lateinit var priceInput: EditText
    private lateinit var uploadBtn: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upload_article, container, false)

        titleInput = view.findViewById(R.id.editArticleTitle)
        contentInput = view.findViewById(R.id.editArticleContent)
        authorInput = view.findViewById(R.id.editArticleAuthor)
        priceInput = view.findViewById(R.id.editArticlePrice)
        uploadBtn = view.findViewById(R.id.btnUploadArticle)
        progressBar = view.findViewById(R.id.uploadArticleProgressBar)

        uploadBtn.setOnClickListener {
            uploadArticle()
        }

        return view
    }

    private fun uploadArticle() {
        val title = titleInput.text.toString().trim()
        val content = contentInput.text.toString().trim()
        val author = authorInput.text.toString().trim()
        val price = priceInput.text.toString().trim().toDoubleOrNull() ?: 0.0
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        if (title.isEmpty() || content.isEmpty() || author.isEmpty()) {
            Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        progressBar.visibility = View.VISIBLE

        val article = Article(
            title = title,
            content = content,
            author = author,
            price = price,
            userId = userId
        )

        FirebaseDatabase.getInstance().getReference("articles")
            .push()
            .setValue(article)
            .addOnSuccessListener {
                progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Article uploaded successfully", Toast.LENGTH_SHORT).show()
                titleInput.text.clear()
                contentInput.text.clear()
                authorInput.text.clear()
                priceInput.text.clear()
            }
            .addOnFailureListener {
                progressBar.visibility = View.GONE
                Toast.makeText(requireContext(), "Upload failed", Toast.LENGTH_SHORT).show()
            }
    }
}

package com.akgroup76.akmax.ui.blog

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.akgroup76.akmax.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class UploadBookFragment : Fragment() {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var uploadButton: Button
    private lateinit var pickFileButton: Button
    private lateinit var selectedUri: Uri

    private val storage = FirebaseStorage.getInstance().reference
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_upload_book, container, false)

        titleEditText = view.findViewById(R.id.titleEditText)
        descriptionEditText = view.findViewById(R.id.descriptionEditText)
        priceEditText = view.findViewById(R.id.priceEditText)
        uploadButton = view.findViewById(R.id.uploadButton)
        pickFileButton = view.findViewById(R.id.pickFileButton)

        pickFileButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            startActivityForResult(intent, 100)
        }

        uploadButton.setOnClickListener {
            uploadBook()
        }

        return view
    }

    private fun uploadBook() {
        val title = titleEditText.text.toString().trim()
        val description = descriptionEditText.text.toString().trim()
        val price = priceEditText.text.toString().trim()

        if (title.isEmpty() || description.isEmpty() || price.isEmpty() || !::selectedUri.isInitialized) {
            Toast.makeText(requireContext(), "Please fill all fields and select a file", Toast.LENGTH_SHORT).show()
            return
        }

        val fileRef = storage.child("books/${System.currentTimeMillis()}.pdf")
        fileRef.putFile(selectedUri)
            .addOnSuccessListener {
                fileRef.downloadUrl.addOnSuccessListener { uri ->
                    val bookData = hashMapOf(
                        "title" to title,
                        "description" to description,
                        "price" to price,
                        "fileUrl" to uri.toString(),
                        "timestamp" to System.currentTimeMillis()
                    )
                    db.collection("books")
                        .add(bookData)
                        .addOnSuccessListener {
                            Toast.makeText(requireContext(), "Book uploaded!", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(requireContext(), "Upload failed", Toast.LENGTH_SHORT).show()
                        }
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            selectedUri = data?.data!!
            Toast.makeText(requireContext(), "File selected", Toast.LENGTH_SHORT).show()
        }
    }
}

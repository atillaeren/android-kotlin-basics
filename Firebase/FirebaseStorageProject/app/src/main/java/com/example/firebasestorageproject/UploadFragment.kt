package com.example.firebasestorageproject

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.firebasestorageproject.databinding.FragmentUploadBinding
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.*


class UploadFragment : Fragment() {
    private lateinit var binding: FragmentUploadBinding
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUploadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null) {
            // do
        } else {
            signInAnonymously()
        }
    }

    private fun signInAnonymously() {
        auth.signInAnonymously()
            .addOnSuccessListener(requireActivity(), OnSuccessListener<AuthResult?> {
                // do
            })
            .addOnFailureListener(requireActivity(),
                OnFailureListener { exception ->
                    Log.e(
                        TAG,
                        "signInAnonymously:FAILURE",
                        exception
                    )
                })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        binding.apply {
            val pickMedia =
                registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                    if (uri != null) {
                        val imageUri = Uri.parse(uri.toString())
                        uploadIV.setImageURI(imageUri)
                        uploadBtn.visibility = View.VISIBLE
                        uploadBtn.setOnClickListener {
                            uploadPhoto(imageUri)
                        }
                    }
                }
            uploadIV.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
    }

    private fun uploadPhoto(imageUri: Uri?) {
        if (imageUri == null) return

        // Generate a unique filename for the photo
        val filename = UUID.randomUUID().toString()

        // Get a reference to the Firebase Storage instance
        val storageRef = Firebase.storage.reference

        // Create a reference to the photo file in Firebase Storage
        val photoRef = storageRef.child("photos/$filename")

        // Upload the photo file to Firebase Storage
        photoRef.putFile(imageUri)
            .addOnSuccessListener {
                // If the upload is successful, get the download URL for the photo
                photoRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                    setFragmentResult(
                        Keys.REQUEST_KEY,
                        bundleOf(Keys.BUNDLE_KEY to downloadUrl.toString())
                    )
                    findNavController().navigate(UploadFragmentDirections.actionUploadFragmentToDownloadFragment())
                }
            }
            .addOnFailureListener { exception ->
                Log.e(ContentValues.TAG, "Error uploading photo", exception)
                // Handle the error
            }

    }
}
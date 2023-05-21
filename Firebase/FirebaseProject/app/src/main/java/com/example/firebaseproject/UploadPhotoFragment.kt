package com.example.firebaseproject

import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.firebaseproject.databinding.FragmentUploadPhotoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.util.*


class UploadPhotoFragment : Fragment() {
    private lateinit var binding: FragmentUploadPhotoBinding
    private lateinit var auth: FirebaseAuth



    companion object {
        const val REQUEST_CODE_PERMISSION = 100
        const val REQUEST_CODE_PICK_IMAGE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUploadPhotoBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.uploadIV.setOnClickListener {

            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_MEDIA_IMAGES)
                == PackageManager.PERMISSION_GRANTED) {
                // 3. If you have permission, start the gallery intent
                pickImageFromGallery()
            } else {
                // 4. If you don't have permission, request it
                requestGalleryPermission()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestGalleryPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
            REQUEST_CODE_PERMISSION
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // If permission is granted, start the gallery intent
                pickImageFromGallery()
            } else {
                // If permission is not granted, show a message or handle the error
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {

            val imageUri = data?.data
            println("imageuri is this $imageUri")
            val uploaderUid = auth.currentUser?.uid

            binding.uploadIV.setImageURI(imageUri)

            binding.uploadBtn.setOnClickListener {
                if (uploaderUid != null) {
                    uploadPhoto(imageUri,uploaderUid)
                }
            }
        }
    }
    /*
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
            .addOnSuccessListener { taskSnapshot ->
                // If the upload is successful, get the download URL for the photo
                photoRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                    // Save the download URL to Firestore
                    savePhotoToFirestore(downloadUrl.toString())
                }
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error uploading photo", exception)
                // Handle the error
            }
    }

    private fun savePhotoToFirestore(downloadUrl: String) {
        val db = Firebase.firestore

        // Create a new photo object with the download URL
        val photo = hashMapOf(
            "url" to downloadUrl
        )

        // Add the photo object to the "photos" collection in Firestore
        db.collection("photos")
            .add(photo)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                // Handle the success
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error adding document", e)
                // Handle the error
            }
    }
     */

    private fun uploadPhoto(imageUri: Uri?, uploaderUid: String) {
        if (imageUri == null) return

        // Generate a unique filename for the photo
        val filename = UUID.randomUUID().toString()

        // Get a reference to the Firebase Storage instance
        val storageRef = FirebaseStorage.getInstance().reference

        // Create a reference to the photo file in Firebase Storage
        val photoRef = storageRef.child("photos/$filename")

        // Upload the photo file to Firebase Storage
        photoRef.putFile(imageUri)
            .addOnSuccessListener {
                // If the upload is successful, get the download URL for the photo
                photoRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                    // Save the download URL to Firestore along with the uploaderUid and comments
                    savePhotoToFirestore(downloadUrl.toString(), uploaderUid)
                }
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error uploading photo", exception)
                // Handle the error
            }
    }

    private fun savePhotoToFirestore(downloadUrl: String, uploaderUid: String) {
        val db = Firebase.firestore

        // Create a new photo object with the download URL, uploaderUid, and comments
        val photo = hashMapOf(
            "url" to downloadUrl,
            "uploaderUid" to uploaderUid
        )

        // Add the photo object to the "photos" collection in Firestore
        db.collection("photos")
            .add(photo)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                // Handle the success
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error adding document", e)
                // Handle the error
            }
    }


}
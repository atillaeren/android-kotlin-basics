package com.example.pickerproject

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.pickerproject.databinding.FragmentImagePickBinding
import com.example.pickerproject.databinding.FragmentMainPageBinding


class ImagePickFragment : Fragment() {
    private lateinit var binding: FragmentImagePickBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentImagePickBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                PickerObject.obj.imageUri = uri.toString()
                println(uri.toString())
                val imageUri = Uri.parse(uri.toString())
                binding.imageView2.setImageURI(imageUri)
            }
        }

        binding.imageView2.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.button2.setOnClickListener {
            findNavController().navigate(ImagePickFragmentDirections.actionImagePickFragmentToFontPickFragment())
        }
    }


}
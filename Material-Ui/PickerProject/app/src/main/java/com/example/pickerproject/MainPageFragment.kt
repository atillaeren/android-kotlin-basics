package com.example.pickerproject

import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.pickerproject.databinding.FragmentFontPickBinding
import com.example.pickerproject.databinding.FragmentMainPageBinding


class MainPageFragment : Fragment() {
    private lateinit var binding: FragmentMainPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            textView.text = PickerObject.obj.name
            textView2.text = PickerObject.obj.age.toString()
            textView.typeface = ResourcesCompat.getFont(requireContext(),PickerObject.obj.font)
            textView2.typeface = ResourcesCompat.getFont(requireContext(),PickerObject.obj.font)
            val imageUri = Uri.parse(PickerObject.obj.imageUri)
            imageView.setImageURI(imageUri)
        }

        binding.button.setOnClickListener {
            findNavController().navigate(MainPageFragmentDirections.actionMainPageFragmentToImagePickFragment())
        }
    }


}
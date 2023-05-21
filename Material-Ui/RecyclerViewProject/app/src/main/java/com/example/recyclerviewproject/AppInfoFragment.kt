package com.example.recyclerviewproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.recyclerviewproject.databinding.FragmentAppInfoBinding


class AppInfoFragment : Fragment() {
    private lateinit var binding: FragmentAppInfoBinding
    private val args by navArgs<AppInfoFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.data?.let {
            binding.apply {
                nameTV.text = it.appName
                releaseDateTV.text = it.appReleaseDate
                categoryTV.text = it.appCategory
                imageView.setImageResource(it.appIcon)
                val url = it.appStoreUrl
                urlTV.setOnClickListener {
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(browserIntent)
                }
            }
        }
    }
}
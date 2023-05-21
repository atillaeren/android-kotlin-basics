package com.example.viewpagerproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewpagerproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val background1 = Color.argb(255, 0, 255, 255)

        val background2 = Color.argb(255, 147, 112, 219)

        val background3 = Color.argb(255, 135, 206, 250)



        val images = listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
        )

        val subtitles = listOf(
            "FeelsOkayMan",
            "EZ",
            "PepeHands"
        )

        val backgrounds = listOf(
            background1,
            background2,
            background3
        )

        val adapter = ViewPagerAdapter(images,subtitles, backgrounds)
        binding.viewPager.adapter = adapter

    }
}
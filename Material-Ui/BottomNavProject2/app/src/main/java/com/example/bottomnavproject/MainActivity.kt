package com.example.bottomnavproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bottomnavproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            bottomNavView.setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.item1 -> {
                        loadFragment(HomeFragment())
                        true
                    }

                    R.id.item2 -> {
                        loadFragment(HeadquarterFragment())
                        true
                    }

                    R.id.item3 -> {
                        loadFragment(WarzoneFragment())
                        true
                    }

                    R.id.item4 -> {
                        loadFragment(EndFragment())
                        true
                    } else -> false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,fragment)
        transaction.commit()
    }
}
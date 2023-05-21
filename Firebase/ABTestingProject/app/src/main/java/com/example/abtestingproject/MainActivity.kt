package com.example.abtestingproject

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.abtestingproject.databinding.ActivityMainBinding
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseInstallations.getInstance().getToken(/* forceRefresh */ true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Installations", "Installation auth token: " + task.result?.token)
                } else {
                    Log.e("Installations", "Unable to get Installation auth token")
                }
            }

        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d(ContentValues.TAG, "Config params updated: $updated")
                    Toast.makeText(
                        applicationContext, "Fetch and activate succeeded",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext, "Fetch failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                changeValue()
            }
    }

    private fun changeValue() {
        val remoteConfig = Firebase.remoteConfig

        // [START get_config_values]
        val getBoolean = remoteConfig[BUTTON_VALUE].asBoolean()
        // [END get_config_values]


        val params = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        val params2 = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        val params3 = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        binding.apply {
            params.apply {
                topToTop = constraint.id
                rightToRight = constraint.id
                leftToLeft = constraint.id
                setMargins(-630, 1600, 0, 0)
            }

            params2.apply {
                topToTop = constraint.id
                rightToRight = constraint.id
                leftToLeft = constraint.id
                setMargins(0, 1600, 0, 0)
            }

            params3.apply {
                topToTop = constraint.id
                rightToRight = constraint.id
                leftToLeft = constraint.id
                setMargins(630, 1600, 0, 0)
            }


            when (getBoolean) {
                true -> return

                false -> {
                    button.apply {
                        layoutParams = params
                        requestLayout()
                    }
                    button2.apply {
                        layoutParams = params2
                        requestLayout()
                    }
                    button3.apply {
                        layoutParams = params3
                        requestLayout()
                    }
                }
            }
        }
    }

    companion object {
        // Remote Config keys
        private const val BUTTON_VALUE = "buttons_position"
    }

}
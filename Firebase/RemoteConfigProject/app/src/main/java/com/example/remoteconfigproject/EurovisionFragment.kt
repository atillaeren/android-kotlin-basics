package com.example.remoteconfigproject

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.remoteconfigproject.databinding.FragmentEurovisionBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import io.grpc.internal.ServiceConfigUtil


class EurovisionFragment : Fragment() {
    private lateinit var binding: FragmentEurovisionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEurovisionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d(TAG, "Config params updated: $updated")
                    Toast.makeText(requireContext(), "Fetch and activate succeeded",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Fetch failed",
                        Toast.LENGTH_SHORT).show()
                }
                changeValue()
            }
    }

    private fun changeValue() {
        val remoteConfig = Firebase.remoteConfig

        // [START get_config_values]
        val getBoolean = remoteConfig[IMAGE_VALUE].asBoolean()
        val getString = remoteConfig[STRING_VALUE].asString()
        val getNumber = remoteConfig[NUMBER_VALUE].asLong()
        // [END get_config_values]
        binding.eurovisionIV.isVisible = getBoolean
        binding.string.text = getString
        binding.number.text = getNumber.toString()

    }

    companion object {
        // Remote Config keys
        private const val IMAGE_VALUE = "imageview_visibility"
        private const val STRING_VALUE = "title_string"
        private const val NUMBER_VALUE = "description_number"
    }

}
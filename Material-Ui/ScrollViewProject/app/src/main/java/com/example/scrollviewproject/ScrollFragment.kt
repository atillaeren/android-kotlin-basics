package com.example.scrollviewproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.scrollviewproject.databinding.FragmentScrollBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ScrollFragment : Fragment()  {

    private lateinit var binding: FragmentScrollBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentScrollBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            scrollView.viewTreeObserver.addOnScrollChangedListener {
                val viewSc = scrollView.getChildAt(scrollView.childCount - 1) as View
                val diff = viewSc.bottom - (scrollView.height + scrollView.scrollY)

                if (diff == 0) {
                    MaterialAlertDialogBuilder(requireContext())
                        .setMessage("You have reached the end of the scroll view.")
                        .show()
                    //Toast.makeText(requireContext(),"You have reached the end of the scroll view.",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
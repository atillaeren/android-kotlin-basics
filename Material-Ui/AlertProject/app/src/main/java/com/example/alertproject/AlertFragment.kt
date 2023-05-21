package com.example.alertproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.alertproject.databinding.FragmentAlertBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


class AlertFragment : Fragment() {
    private lateinit var binding: FragmentAlertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAlertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            noButton.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Emergency")
                    .setMessage("Attention! Be careful..")
                    .show()

            }
            oneButton.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("One Button Title")
                    .setMessage("Click the print button for show..")
                    .setPositiveButton("Print") { _, _ -> println(oneButton.text) }
                    .show()
            }
            twoButton.setOnClickListener {

                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Two Button Title")
                    .setMessage("Click the buttons to see the action..")
                    .setPositiveButton("Yes") { _, _ ->
                        Toast.makeText(
                            requireContext(),
                            "Yes, this is it..",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .setNegativeButton("No") { _, _ ->
                        Toast.makeText(
                            requireContext(),
                            "Nooooo..",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .show()
            }
            threeButton.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Three Button Title")
                    .setMessage("Click to see different messages..")
                    .setPositiveButton("Ok") { _, _ ->
                        Toast.makeText(
                            requireContext(),
                            "Ok, go on..",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .setNegativeButton("Not ok") { _, _ ->
                        Toast.makeText(
                            requireContext(),
                            "Not ok, test others..",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .setNeutralButton("Neutral") { _, _ ->
                        Toast.makeText(
                            requireContext(),
                            "This is a neutral button..",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .show()
            }
            etButton.setOnClickListener {
                val editText = EditText(requireContext())
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("EditText Title")
                    .setMessage("Click the button for print edittext..")
                    .setView(editText)
                    .setPositiveButton("Print EditText") { _, _ ->
                        Toast.makeText(
                            requireContext(),
                            editText.text,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .show()
            }
            bsButton.setOnClickListener {
                showBottomSheetDialog()
            }
            shareButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra("Share this..", "ShareSheet Project")

                startActivity(intent)
            }
        }
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()

        val button = view.findViewById<Button>(R.id.option1)
        val button2 = view.findViewById<Button>(R.id.option2)
        button.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Option1",
                Toast.LENGTH_SHORT
            ).show()
        }

        button2.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Option2",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
package com.coding.sibisa.ui.fragment

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.coding.sibisa.R
import com.coding.sibisa.ui.latihan.LatihanActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LatihanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LatihanFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latihan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intentToLatihan()
    }

    private fun intentToLatihan() {
        val cvLatihan = view?.findViewById<CardView>(R.id.cv_latihan)

        cvLatihan?.setOnClickListener {
            val intent = Intent(requireContext(), LatihanActivity::class.java)
            intent.putExtra("itemId", 1)
            startActivityForResult(intent, 123)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123 && resultCode == Activity.RESULT_OK) {
            val message = data?.getStringExtra("message")
            // Use the message here, for example, Log.d()
            showConfirmationDialog(requireContext(), message!!) {
                Log.d("TAG", "Received message: $message")
            }

        }
    }

    private fun showConfirmationDialog(context: Context, message: String, positiveAction: () -> Unit) {
        val alertDialogBuilder = AlertDialog.Builder(context)

        // Set the message for the dialog
        alertDialogBuilder.setMessage(message)

        // Set the positive button and its click listener
        alertDialogBuilder.setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
            // Call the positive action callback when the user clicks OK
            positiveAction.invoke()
            dialog.dismiss() // Close the dialog
        }

        // Create and show the dialog
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LatihanFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic

        fun newInstance(param1: String, param2: String) =
            LatihanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
package com.coding.sibisa.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.coding.sibisa.R
import com.coding.sibisa.ui.BelajarHurufActivity
import com.coding.sibisa.ui.BelajarKataActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MateriFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MateriFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

//        val intent = Intent(requireContext(), BelajarHurufActivity::class.java)
//        intent.putExtra(param1, "ada")
//        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_materi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intentToCvHuruf()
        intentToCvKata()

    }

    private fun intentToCvKata() {
        val cardKata = view?.findViewById<CardView>(R.id.cv_kata)

        cardKata?.setOnClickListener{
            val intent = Intent(requireContext(), BelajarKataActivity::class.java)
            startActivity(intent)
        }
    }

    private fun intentToCvHuruf() {
        val cardHuruf = view?.findViewById<CardView>(R.id.cv_huruf)
        cardHuruf?.setOnClickListener {
            val intent = Intent(requireContext(), BelajarHurufActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MateriFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}
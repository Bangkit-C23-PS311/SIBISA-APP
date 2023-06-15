package com.coding.sibisa.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.coding.sibisa.R
import com.coding.sibisa.data.model.MainVM
import com.coding.sibisa.data.model.VMFactory
import com.coding.sibisa.data.pref.Compact
import com.coding.sibisa.data.response.DataItemItem
import com.coding.sibisa.databinding.FragmentProgressBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProgressFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProgressFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentProgressBinding
    private lateinit var mainView: MainVM
    private lateinit var vmFactory: VMFactory

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
        return inflater.inflate(R.layout.fragment_progress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProgressBinding.bind(view)

        vmFactory = VMFactory.getInstanceFragment(this)
        mainView = ViewModelProvider(this, vmFactory)[MainVM::class.java]

        mainView.getMyUser().observe(viewLifecycleOwner, {
            mainView.getProgress(it).observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    when (result) {
                        is Compact.Loading -> {
//                            showLoading(true)
                        }
                        is Compact.Succes -> {
                            val progress = result.data
                            val progresMateriHuruf = progress.materi?.jsonMember1
                            val progresMateriKata = progress.materi?.jsonMember2
                            val progresLatihan1 = progress.latihan?.jsonMember1
                            val progresLatihan2 = progress.latihan?.jsonMember2

                            binding.tvBelajarHuruf.text = "Belajar Huruf : ${progresMateriHuruf}"
                            binding.tvBelajarKata.text = "Belajar Kata : ${progresMateriKata}"
                            binding.tvLatihan1.text = "Latihan 1 : ${progresLatihan1}"
                            binding.tvLatihan2.text = "Latihan 2 : ${progresLatihan2}"

                        }
                        is Compact.Error -> {
                            val errorMessage = result.error
                            Log.d("BelajarHurufActivity", "error: $errorMessage")
//                            showLoading(false)
                        }
                    }
                }
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProgressFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProgressFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
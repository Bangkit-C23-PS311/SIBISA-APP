package com.coding.sibisa.ui.fragment

import MateriAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.coding.sibisa.R
import com.coding.sibisa.data.model.MainVM
import com.coding.sibisa.data.pref.Compact
import com.coding.sibisa.data.response.CategoryResponse
import com.coding.sibisa.data.response.DataItem
import com.coding.sibisa.ui.BelajarHurufActivity
import com.coding.sibisa.ui.BelajarKataActivity

private const val TOKEN = ""


class MateriFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var mainVM: MainVM
    private var token: String? = ""
    private lateinit var adapter: MateriAdapter
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            token = it.getString(TOKEN)
        }

        Log.d("FRAG", mainVM.getCategoryItem(token!!).toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the LiveData from your LifecycleCoroutineScope
        val categoryResponseLiveData: LiveData<Compact<CategoryResponse>> =   mainVM.getCategoryItem(token!!)
        Transformations.map(categoryResponseLiveData) { compact ->
            compact.data?.items ?: emptyList()
        }

        // Observe the LiveData for changes
        myLiveData.observe(viewLifecycleOwner, { data ->
            // Update the data in your adapter
            adapter.dataList = data
            adapter.notifyDataSetChanged()
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
        fun newInstance(token: String, mainVM: MainVM): MateriFragment {
            val fragment = MateriFragment()
            val args = Bundle().apply {
                putString(TOKEN, token)
            }
            fragment.arguments = args
            fragment.mainVM = mainVM
            return fragment
        }
    }



}
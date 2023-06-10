package com.coding.sibisa.ui.fragment

import MateriAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coding.sibisa.data.model.MainVM
import com.coding.sibisa.data.model.VMFactory


private const val TOKEN = ""


class MateriFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var mainVM: MainVM
    private lateinit var pref: VMFactory
    private var token: String? = ""
    private lateinit var adapter: MateriAdapter
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            token = it.getString(TOKEN)
        }

        pref = VMFactory.getInstanceFragment(this)
        mainVM = ViewModelProvider(this, pref).get(MainVM::class.java)

        setUpView()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        }

    private fun setUpView() {

        mainVM.getMyUser().observe(viewLifecycleOwner){
            if (it.isLogin){
                setData()
            }
        }
    }


    private fun setData() {
       mainVM.getMyUser().observe(viewLifecycleOwner){
           val code = "${it.token}"
           mainVM.getCategoryItem(code).observe(viewLifecycleOwner){
               adapter = adapter

           }

       }
    }

//    private fun myCategory() {
//        mainVM.getMyUser().observe(viewLifecycleOwner){
//            val code = "${it.token}"
//            mainVM.getCategoryItem(code).observe(viewLifecycleOwner){
//                if (it != null){
//                    if (it is Compact.Error){
//                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
//                    }else if (it is Compact.Succes){
//                        setData(it.data.data)
//                    }
//                }
//            }
//        }
//    }

//    private fun setData(data: List<List<DataItem>>) {
//        data.forEach{
//
//        }
//    }

    // Observe the LiveData for changes







//    private fun intentToCvHuruf() {
//        val cardHuruf = view?.findViewById<CardView>(R.id.cv_huruf)
//        cardHuruf?.setOnClickListener {
//            val intent = Intent(requireContext(), BelajarHurufActivity::class.java)
//            startActivity(intent)
//        }
//    }

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
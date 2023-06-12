package com.coding.sibisa.ui.belajarkata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coding.sibisa.R
import com.coding.sibisa.data.model.MainVM
import com.coding.sibisa.data.model.VMFactory
import com.coding.sibisa.data.pref.Compact
import com.coding.sibisa.data.response.DataItemItem
import com.coding.sibisa.databinding.ActivityBelajarHurufBinding
import com.coding.sibisa.databinding.ActivityBelajarKataBinding
import com.coding.sibisa.ui.materi.HurufAdapter
import com.coding.sibisa.ui.MainActivity

class BelajarKataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBelajarKataBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var hurufAdapter: HurufAdapter
    private var dataList = mutableListOf<DataItemItem>()
    private lateinit var vmFactory: VMFactory
    private lateinit var mainVM: MainVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBelajarKataBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val categoryId = intent.getIntExtra("itemId", -1)


        binding.btnBackKata.setOnClickListener{
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.kata_rv)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        hurufAdapter = HurufAdapter(applicationContext)
        recyclerView.adapter = hurufAdapter

        vmFactory = VMFactory.getInstance(this)
        mainVM = ViewModelProvider(this, vmFactory)[MainVM::class.java]
        Log.d("Ngecek", "cek ${hurufAdapter.itemCount}")
        if (hurufAdapter.itemCount < 1 ) {

            mainVM.getMyUser().observe(this, Observer { myUser ->
                mainVM.getMaterial(myUser, categoryId).observe(this) { result ->
                    if (result != null) {
                        when (result) {
                            is Compact.Loading -> {
                                showLoading(true)
                            }
                            is Compact.Succes -> {
                                val categoriesData = result.data
                                categoriesData.data?.forEach { dataItem ->
                                    val dataResponse: List<DataItemItem> =
                                        dataItem as List<DataItemItem>

                                    dataResponse.forEach { dataItem ->
                                        val title = dataItem.title
                                        if (title != null) {
                                            dataList.add(dataItem)
                                        }
                                    }

                                    hurufAdapter.setDataList(dataList)
                                    showLoading(false)
                                }
                            }
                            is Compact.Error -> {
                                val errorMessage = result.error
                                Log.d("BelajarHurufActivity", "error: $errorMessage")
                                showLoading(false)
                            }
                        }
                    }
                }
            })
        }


//        dataList.add(HurufDataModel("A", R.drawable.ic_baseline_local_library_24))
//        dataList.add(HurufDataModel("B", R.drawable.ic_baseline_local_library_24))
//        dataList.add(HurufDataModel("C", R.drawable.ic_baseline_local_library_24))
//        dataList.add(HurufDataModel("D", R.drawable.ic_baseline_local_library_24))
//        dataList.add(HurufDataModel("E", R.drawable.ic_baseline_local_library_24))
//        dataList.add(HurufDataModel("F", R.drawable.ic_baseline_local_library_24))



    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbKata.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}
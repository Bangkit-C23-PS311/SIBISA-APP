package com.coding.sibisa.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coding.sibisa.R
import com.coding.sibisa.databinding.ActivityBelajarHurufBinding
import com.coding.sibisa.ui.materi.HurufAdapter
import com.coding.sibisa.ui.materi.HurufDataModel

class BelajarHurufActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBelajarHurufBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var hurufAdapter: HurufAdapter
    private var dataList = mutableListOf<HurufDataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBelajarHurufBinding.inflate(layoutInflater)
        setContentView(binding.root)




        recyclerView = findViewById(R.id.rv_huruf)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        hurufAdapter = HurufAdapter(applicationContext)
        recyclerView.adapter = hurufAdapter

        dataList.add(HurufDataModel("A", R.drawable.ic_baseline_local_library_24))
        dataList.add(HurufDataModel("B", R.drawable.ic_baseline_local_library_24))
        dataList.add(HurufDataModel("C", R.drawable.ic_baseline_local_library_24))
        dataList.add(HurufDataModel("D", R.drawable.ic_baseline_local_library_24))
        dataList.add(HurufDataModel("E", R.drawable.ic_baseline_local_library_24))
        dataList.add(HurufDataModel("F", R.drawable.ic_baseline_local_library_24))

        hurufAdapter.setDataList(dataList)

        binding.btnBackHuruf.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }


}
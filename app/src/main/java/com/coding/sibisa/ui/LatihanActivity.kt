package com.coding.sibisa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coding.sibisa.databinding.ActivityLatihanBinding

class LatihanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLatihanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLatihanBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
package com.coding.sibisa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coding.sibisa.databinding.ActivityBelajarKataBinding

class BelajarKataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBelajarKataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBelajarKataBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
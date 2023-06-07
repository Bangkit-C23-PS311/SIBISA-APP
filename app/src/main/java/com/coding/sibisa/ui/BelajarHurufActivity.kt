package com.coding.sibisa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coding.sibisa.databinding.ActivityBelajarHurufBinding

class BelajarHurufActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBelajarHurufBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBelajarHurufBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
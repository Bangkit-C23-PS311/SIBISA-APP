package com.coding.sibisa.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.coding.sibisa.R
import com.coding.sibisa.auth.LoginActivity
import com.coding.sibisa.data.model.AuthVM
import com.coding.sibisa.data.model.MainVM
import com.coding.sibisa.data.model.VMFactory
import com.coding.sibisa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var  authVM: AuthVM
    private lateinit var vmFactory: VMFactory
    private lateinit var mainVM: MainVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vmFactory = VMFactory.getInstance(this)
        authVM = ViewModelProvider(this, vmFactory)[AuthVM::class.java]
        mainVM = ViewModelProvider(this, vmFactory)[MainVM::class.java]

        logout()

    }

    private fun logout() {
        binding.logoutButton.setOnClickListener {
            authVM.logout()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
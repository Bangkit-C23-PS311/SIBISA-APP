package com.coding.sibisa.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.coding.sibisa.data.model.AuthVM
import com.coding.sibisa.data.model.VMFactory
import com.coding.sibisa.data.pref.Compact
import com.coding.sibisa.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var authVM: AuthVM
    private lateinit var vmFactory: VMFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vmFactory = VMFactory.getInstance(this)
        authVM = ViewModelProvider(this, vmFactory)[AuthVM::class.java]

        setUpView()
    }
    private fun valid() =
        binding.ETEmailRegister.error == null && binding.ETPasswordRegister.error == null && binding.ETNameRegister.error == null && !binding.ETEmailRegister.text.isNullOrEmpty() && !binding.ETNameRegister.text.isNullOrEmpty() && !binding.ETPasswordRegister.text.isNullOrEmpty()
    private fun setUpView() {
        binding.btnCreate.setOnClickListener {
            if (valid()) {
                val name = binding.ETNameRegister.text.toString()
                val email = binding.ETEmailRegister.text.toString()
                val pass = binding.ETPasswordRegister.text.toString()
                authVM.getRegister(name,email, pass).observe(this){
                    when(it){
                        is Compact.Succes -> {
                            showLoading(false)
                            Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        is Compact.Loading -> showLoading(true)
                        is Compact.Error -> {
                            Toast.makeText(this, "Register Gagal", Toast.LENGTH_SHORT).show()
                            showLoading(false)
                        }
                    }
                }
            }
        }
    }


    private fun showLoading(isLoading: Boolean) {
        binding.pbRegis.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}
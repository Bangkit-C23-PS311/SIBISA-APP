package com.coding.sibisa.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.coding.sibisa.data.model.AuthVM
import com.coding.sibisa.data.model.VMFactory
import com.coding.sibisa.data.pref.Compact
import com.coding.sibisa.data.pref.UserPreference
import com.coding.sibisa.data.response.MyUser
import com.coding.sibisa.data.response.User
import com.coding.sibisa.databinding.ActivityLoginBinding
import com.coding.sibisa.ui.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var authVM: AuthVM
    private lateinit var vmFactory: VMFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vmFactory = VMFactory.getInstance(this)
        authVM = ViewModelProvider(this,vmFactory)[AuthVM::class.java]

        setUpRegister()
        SetUpView()

    }

    private fun SetUpView() {
        binding.buttonLogin.setOnClickListener {
            val email = binding.etEmailLogin.text.toString()
            val pass = binding.etPasswordLogin.text.toString()
            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
                authVM.getLogin(email, pass).observe(this){
                    if (it != null){
                        when(it){
                            is Compact.Succes -> {
                                showLoading(false)
                                val result = it.data
                                saveMyUser(
                                    MyUser(
                                        result.data?.user?.name.toString(),
                                        true,
                                        result.data?.user?.token.toString()
                                    )
                                )
                                val i = Intent(this, MainActivity::class.java)
                                startActivity(i)
                                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            is Compact.Error -> {
                                Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
                                showLoading(false)
                            }
                            is Compact.Loading -> showLoading(true)
                        }
                    }
                }
            }
        }
    }

    private fun saveMyUser(myUser: MyUser) {
        authVM.saveMyUser(myUser)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbLogin.apply {
            visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun setUpRegister() {
        binding.buttonRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
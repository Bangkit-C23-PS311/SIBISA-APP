package com.coding.sibisa.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.coding.sibisa.ui.auth.LoginActivity
import com.coding.sibisa.data.model.AuthVM
import com.coding.sibisa.data.model.MainVM
import com.coding.sibisa.data.model.VMFactory
import com.coding.sibisa.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private val SPLASH_SCREEN_DURATION = 5000
    private lateinit var vmFactory: VMFactory
    private lateinit var mainVM: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



        vmFactory = VMFactory.getInstance(this)
        mainVM = ViewModelProvider(this, vmFactory)[MainVM::class.java]

//        Handler().postDelayed({
//            val intent = Intent(this@SplashScreen, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }, SPLASH_SCREEN_DURATION.toLong())
        setUpSplash()
    }

    private fun setUpSplash() {
        mainVM.getMyUser().observe(this) {token ->
            if (token.token.isNullOrEmpty()){
                Intent(this, LoginActivity::class.java).also {
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, 3000)
                }
            }else {
                Intent(this, MainActivity::class.java).also {
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, 3000)
                }
            }
        }
    }
}
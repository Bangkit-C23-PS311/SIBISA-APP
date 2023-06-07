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
import androidx.viewpager2.widget.ViewPager2
import com.coding.sibisa.R
import com.coding.sibisa.ui.fragment.FragmentAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: FragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager2 = findViewById(R.id.viewpager2)

        adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        tabLayout.addTab(tabLayout.newTab().setText("Materi"))
        tabLayout.addTab(tabLayout.newTab().setText("Latihan"))

        viewPager2.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null){
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }
}
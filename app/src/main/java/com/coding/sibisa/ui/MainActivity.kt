package com.coding.sibisa.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.coding.sibisa.R
import com.coding.sibisa.ui.fragment.FragmentAdapter
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.coding.sibisa.data.api.ApiService
import com.coding.sibisa.ui.auth.LoginActivity
import com.coding.sibisa.data.model.AuthVM
import com.coding.sibisa.data.model.MainVM
import com.coding.sibisa.data.model.VMFactory
import com.coding.sibisa.data.pref.UserPreference
import com.coding.sibisa.data.pref.dataStore
import com.coding.sibisa.data.repo.Repository
import com.coding.sibisa.data.response.MyUser
import com.coding.sibisa.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "INMI"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var authVM: AuthVM
    private lateinit var vmFactory: VMFactory
    private lateinit var mainVM: MainVM
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: FragmentAdapter
    private lateinit var bottomBar: BottomNavigationView
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout = findViewById(R.id.tab_layout)
        viewPager2 = findViewById(R.id.viewpager2)
        bottomBar = findViewById(R.id.bottom_navbar)

        val userPreference = UserPreference.getInstance(this.dataStore)

        vmFactory = VMFactory.getInstance(this)
        authVM = ViewModelProvider(this, vmFactory)[AuthVM::class.java]
        mainVM = ViewModelProvider(this, vmFactory)[MainVM::class.java]

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            userPreference.getToken().firstOrNull()?.let { myUser ->
                binding.usernameTextView.text = "Hello, ${myUser.name}"
                token = myUser.token
            }
        }


        adapter = FragmentAdapter(supportFragmentManager, lifecycle, mainVM, token)
        tabLayout.addTab(tabLayout.newTab().setText("Materi"))
        tabLayout.addTab(tabLayout.newTab().setText("Latihan"))

        viewPager2.adapter = adapter



        logout()




        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })




        bottomBar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.home -> {
                    true
                }

                else -> false
            }
        }


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


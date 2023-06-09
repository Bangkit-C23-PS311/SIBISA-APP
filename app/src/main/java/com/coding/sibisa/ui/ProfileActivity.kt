package com.coding.sibisa.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.viewpager2.widget.ViewPager2
import com.coding.sibisa.R
import com.coding.sibisa.ui.fragment.FragmentAdapter
import com.google.android.material.tabs.TabLayout

class ProfileActivity : AppCompatActivity() {

    private lateinit var tabLayoutProfile: TabLayout
    private lateinit var viewPager2Profile: ViewPager2
    private lateinit var adapterProfile: FragmentAdapter
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tabLayoutProfile = findViewById(R.id.tab_layout_profile)
        viewPager2Profile = findViewById(R.id.viewpager2_profile)
        backButton = findViewById(R.id.btn_back_profile)
        adapterProfile = FragmentAdapter(supportFragmentManager, lifecycle)

        tabLayoutProfile.addTab(tabLayoutProfile.newTab().setText("Progres Belajar"))
        tabLayoutProfile.addTab(tabLayoutProfile.newTab().setText("Profil"))

        tabLayoutProfile.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager2Profile.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        viewPager2Profile.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayoutProfile.selectTab(tabLayoutProfile.getTabAt(position))
            }
        })

        backButton.setOnClickListener{
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
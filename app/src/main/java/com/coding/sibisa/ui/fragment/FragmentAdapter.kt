package com.coding.sibisa.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.coding.sibisa.data.model.MainVM

class FragmentAdapter (
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
   private val mainVM: MainVM,
    private val token: String,
) : FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            MateriFragment.newInstance(token,  mainVM)
        else
            LatihanFragment()
    }

}
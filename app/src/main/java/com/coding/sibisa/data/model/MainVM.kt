package com.coding.sibisa.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.coding.sibisa.data.pref.Compact
import com.coding.sibisa.data.response.MyUser
import com.coding.sibisa.data.repo.Repository
import com.coding.sibisa.data.response.CategoryResponse
import com.coding.sibisa.data.response.DataItem

class MainVM(private val repo: Repository): ViewModel() {

    fun getMyUser(): LiveData<MyUser>{
        return repo.myUser()
    }



    fun getCategoryItem(token: String) = repo.getCategory(token)

}
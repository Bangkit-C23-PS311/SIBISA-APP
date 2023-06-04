package com.coding.sibisa.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.coding.sibisa.data.response.MyUser
import com.coding.sibisa.repo.Repository

class MainVM(private val repo: Repository): ViewModel() {

    fun getMyUser(): LiveData<MyUser>{
        return repo.myUser()
    }
}
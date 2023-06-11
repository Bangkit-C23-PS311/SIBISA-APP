package com.coding.sibisa.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.coding.sibisa.data.response.MaterialResponse
import com.coding.sibisa.data.response.MyUser
import com.coding.sibisa.repo.Repository
import kotlinx.coroutines.launch

class MainVM(private val repo: Repository): ViewModel() {

    fun getMyUser(): LiveData<MyUser>{
        return repo.myUser()
    }

    fun getMaterial(myUser: MyUser, categoryId: Int) = repo.getMaterial(myUser.token, categoryId)

}
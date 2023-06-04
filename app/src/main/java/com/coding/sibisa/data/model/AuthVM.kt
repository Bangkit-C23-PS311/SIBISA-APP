package com.coding.sibisa.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.sibisa.data.pref.UserPreference
import com.coding.sibisa.data.response.MyUser
import com.coding.sibisa.repo.Repository
import kotlinx.coroutines.launch


class AuthVM(private val repo: Repository): ViewModel() {

    fun saveMyUser(myUser: MyUser){
        viewModelScope.launch {
            repo.saveMyUser(myUser)
        }
    }

    fun login(){
        viewModelScope.launch {
            repo.login()
        }
    }

    fun getLogin(email: String, pass: String) = repo.myLogin(email, pass)

    fun getRegister(name: String, email: String, pass: String) = repo.myRegister(name, email, pass)

    fun logout(){
        viewModelScope.launch {
            repo.logout()
        }
    }
}
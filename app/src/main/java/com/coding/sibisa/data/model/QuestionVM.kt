package com.coding.sibisa.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.coding.sibisa.data.repo.Repository
import com.coding.sibisa.data.response.MyUser

class QuestionVM(private val repo: Repository): ViewModel() {

    fun getMyUser(): LiveData<MyUser> {
        return repo.myUser()
    }

    fun getQuest(token: String, categoryId: Int) = repo.getQustion(token, categoryId)
}
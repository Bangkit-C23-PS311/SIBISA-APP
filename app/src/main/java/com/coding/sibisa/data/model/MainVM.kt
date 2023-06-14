package com.coding.sibisa.data.model

import androidx.lifecycle.*
import com.coding.sibisa.data.response.MyUser
import com.coding.sibisa.data.repo.Repository
import kotlinx.coroutines.launch

class MainVM(private val repo: Repository): ViewModel() {

    fun getMyUser(): LiveData<MyUser>{
        return repo.myUser()
    }

    fun getMaterial(myUser: MyUser, categoryId: Int) = repo.getMaterial(myUser.token, categoryId)

    fun postDataProgressMaterial(token: String, materialId: Int) {
        viewModelScope.launch {
            repo.postDataProgressMaterial(token, materialId)
        }
    }
    suspend fun postDataProgressPractice(token: String, practiceId: Int, questionId: Int, answer: Boolean) {
        viewModelScope.launch {
            repo.postDataProgressPractice(token, practiceId, questionId, answer)
        }
    }

}
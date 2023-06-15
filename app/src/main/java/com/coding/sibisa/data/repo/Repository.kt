package com.coding.sibisa.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.coding.sibisa.data.api.ApiService
import com.coding.sibisa.data.pref.Compact
import com.coding.sibisa.data.pref.UserPreference
import com.coding.sibisa.data.request.PostMateriRequest
import com.coding.sibisa.data.request.PostPracticeRequest
import com.coding.sibisa.data.response.*


class Repository(private val pref: UserPreference, private val api: ApiService) {
    companion object {
        const val TAG = "INIREPO"

        @Volatile
        private var INSTANCE: Repository? = null
        fun getInstance(pref: UserPreference, api: ApiService): Repository =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: Repository(pref, api)
            }.also { INSTANCE = it }
    }

    suspend fun login(){
        pref.login()
    }

    fun myRegister(name: String, email: String, pass: String): LiveData<Compact<RegisterResponse>> =
        liveData {
            emit(Compact.Loading)
            try {
                val hasil = api.register(ParamRegister(name, email, pass))
                emit(Compact.Succes(hasil))
            }catch (exc: Exception){
                emit(Compact.Error(exc.message.toString()))
                Log.d(TAG, exc.message.toString())
            }
        }


    fun myLogin(email: String, pass: String): LiveData<Compact<LoginResponse>> = liveData {
        emit(Compact.Loading)
        try {
            val hasil = api.login(email, pass)
            emit(Compact.Succes(hasil))
        }catch (exc: Exception){
            emit(Compact.Error(exc.message.toString()))
            Log.d(TAG, exc.message.toString())
        }
    }

    suspend fun logout(){
        pref.logout()
    }

    fun myUser(): LiveData<MyUser>{
        return pref.getToken().asLiveData()
    }

    suspend fun saveMyUser(myUser: MyUser){
        pref.saveToken(myUser)
    }

    fun getMaterial(token: String, categoryId: Int): LiveData<Compact<MaterialResponse>> = liveData{
        emit(Compact.Loading)
        try {
            val result = api.material("Bearer ${token}", categoryId)
            emit(Compact.Succes(result))
        }catch (exc: Exception){
            emit(Compact.Error(exc.message.toString()))
        }
    }

    fun getQustion(token: String, categoryId: Int): LiveData<Compact<QuestionResponse>> = liveData{
        emit(Compact.Loading)
        try {
            val result = api.question("Bearer ${token}", categoryId)
            emit(Compact.Succes(result))
        }catch (exc: Exception){
            emit(Compact.Error(exc.message.toString()))
        }
    }

    suspend fun postDataProgressMaterial(token: String, materialId: Int): Int {
        val requestBody = PostMateriRequest(materialId)
        val response = api.postDataProgressMaterial("Bearer ${token}", requestBody)
        return response.code()
    }

    suspend fun postDataProgressPractice(token: String, practiceId: Int, questionId: Int, answer: Boolean): Int {
        val requestBody = PostPracticeRequest(answer, questionId, practiceId)
        val response = api.postDataProgressPractice("Bearer ${token}", requestBody)
        Log.d("response", "${response.code()}")
        return response.code()
    }

    fun getProgress(token: String): LiveData<Compact<ProgressResponse>> = liveData{
        emit(Compact.Loading)
        try {
            val result = api.progress("Bearer ${token}")
            emit(Compact.Succes(result))
        }catch (exc: Exception){
            emit(Compact.Error(exc.message.toString()))
        }
    }

}
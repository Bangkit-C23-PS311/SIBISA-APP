package com.coding.sibisa.data.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coding.sibisa.data.di.Injection
import com.coding.sibisa.data.repo.Repository

class VMFactory(private val repo: Repository): ViewModelProvider.NewInstanceFactory()  {

    companion object{
        @Volatile
        private var INSTANCE: VMFactory? = null
        fun getInstance(context: Context): VMFactory{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: VMFactory(Injection.provider(context))
            }.also { INSTANCE = it }
        }
    }

    @Suppress("UNCHECKED")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthVM::class.java)){
            return AuthVM(repo) as T
        }
        if (modelClass.isAssignableFrom(MainVM::class.java)){
            return MainVM(repo) as T
        }
        if(modelClass.isAssignableFrom(QuestionVM::class.java)){
            return QuestionVM(repo) as T
        }

        throw IllegalArgumentException("Unknown VM Class" + modelClass.simpleName)
    }


}
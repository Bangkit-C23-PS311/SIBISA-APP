package com.coding.sibisa.data.pref


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.coding.sibisa.data.response.MyUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class UserPreference (private val dataStore: DataStore<Preferences>) {

    companion object{
        @Volatile
        private var INSTANCE : UserPreference? = null

        private val TOKEN_KEY = stringPreferencesKey("token")
        private val LOGIN_KEY = booleanPreferencesKey("login")
        private val USER_KEY = stringPreferencesKey("user")


        fun getInstance(dataStore: DataStore<Preferences>): UserPreference{
            return INSTANCE ?: synchronized(this){
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

    fun getToken(): Flow<MyUser>{
        return dataStore.data.map { pref ->
            MyUser(
                pref[USER_KEY] ?: "",
                pref[LOGIN_KEY] ?: false,
                pref[TOKEN_KEY] ?: "",
            )
        }
    }

    suspend fun saveToken(myUser: MyUser){
        dataStore.edit { pref ->
            pref[USER_KEY] = myUser.name
            pref[LOGIN_KEY] = myUser.isLogin
            pref[TOKEN_KEY] = myUser.token
        }
    }

    suspend fun login(){
        dataStore.edit { pref ->
            pref[LOGIN_KEY] = true
        }
    }

    suspend fun logout(){
        dataStore.edit { pref ->
            pref.clear()
        }
    }



}
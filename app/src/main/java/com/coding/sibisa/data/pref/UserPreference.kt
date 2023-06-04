package com.coding.sibisa.data.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.coding.sibisa.data.response.Data
import com.coding.sibisa.data.response.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference(private val dataStore: DataStore<Preferences>) {
    companion object{
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val TOKEN = stringPreferencesKey("token")
        private val user = stringPreferencesKey("user")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference{
            return INSTANCE ?: synchronized(this){
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
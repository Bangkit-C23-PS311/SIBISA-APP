package com.coding.sibisa.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.coding.sibisa.data.api.ApiConfig
import com.coding.sibisa.data.pref.UserPreference
import com.coding.sibisa.data.pref.dataStore
import com.coding.sibisa.repo.Repository


val Context.dataStore: DataStore<Preferences> by preferencesDataStore("token")

object Injection {
    fun provider(context: Context): Repository{
        val pref = UserPreference.getInstance(context.dataStore)
        val api = ApiConfig.getApiService()
        return Repository.getInstance(pref, api)
    }
}
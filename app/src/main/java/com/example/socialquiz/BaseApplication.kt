package com.example.socialquiz

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

val Context.datastore : DataStore<Preferences> by preferencesDataStore("user")
class BaseApplication : Application() {

    companion object{
        var isLoggedIn by mutableStateOf(false)
        var accessToken : String? = null
    }

    override fun onCreate() {
        super.onCreate()
        baseContext.datastore.data.map {
            accessToken = it[stringPreferencesKey("access_token")]
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

    override fun onTerminate() {
        super.onTerminate()
    }

}
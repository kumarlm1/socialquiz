package com.example.socialquiz.screen.home

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.socialquiz.BaseApplication
import com.example.socialquiz.datastore
import com.example.socialquiz.ktor.LoginRepo
import com.example.socialquiz.responseItems.UserOrgs
import com.example.socialquiz.responseItems.UserOrgsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.descriptors.PrimitiveKind

class HomeScreenViewModel(app : Application) : AndroidViewModel(app) {

    private object PreferenceKeys{
        val accessToken = stringPreferencesKey("access_token")
    }


    private var accessToken  = BaseApplication.accessToken!!

    var isSubmitted by mutableStateOf(false)
    var isLoggedIn by mutableStateOf(BaseApplication.accessToken!=null)

    var userorgs by mutableStateOf(listOf<UserOrgsItem>())

    fun onSubmitButton(){
        isSubmitted = !isSubmitted
    }

    suspend fun storeAccessKey(key : String){
        getApplication<Application>().baseContext.datastore.edit {
            it[PreferenceKeys.accessToken] = key
        }
    }


     fun Login(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
         val res = LoginRepo.Login(email,password)
         if(res != null) {
             accessToken = res.access_token
             BaseApplication.accessToken = res.access_token
             storeAccessKey(res.access_token)
             BaseApplication.isLoggedIn = true
             isLoggedIn = true
             println(res)
             getAllOrgs()
         }
         else
             isSubmitted =false
    }

     private fun getAllOrgs() = viewModelScope.launch(Dispatchers.IO){
        if(isLoggedIn){
           val res =  LoginRepo.getallOrgs(accessToken)
            res?.let {
                userorgs = it
            }
        }
    }


}
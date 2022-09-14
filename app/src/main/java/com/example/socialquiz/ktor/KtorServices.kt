package com.example.socialquiz.ktor

import com.example.socialquiz.responseItems.LoginResponse
import com.example.socialquiz.responseItems.UserOrgs
import com.example.socialquiz.responseItems.UserOrgsItem


interface KtorServices {

    suspend fun login(email : String , password : String) : LoginResponse

    suspend fun getOrgs(auth : String) : List<UserOrgsItem>
}
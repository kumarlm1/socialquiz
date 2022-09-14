package com.example.socialquiz.ktor

import com.example.socialquiz.responseItems.LoginResponse
import com.example.socialquiz.responseItems.UserOrgs
import com.example.socialquiz.responseItems.UserOrgsItem
import io.ktor.client.features.*
import java.io.IOException

object LoginRepo  {

    suspend fun Login(email :String,password :String): LoginResponse? {
        try {
            return KtorServicesImpl.login(email, password)
        } catch (e: ClientRequestException) {
            println(e)
        } catch (e: IOException) {
            println(e)
        }
        return null
    }

    suspend fun getallOrgs(auth : String ) : List<UserOrgsItem>? {
        try {
            return KtorServicesImpl.getOrgs(auth)
        } catch (e: ClientRequestException) {
            println(e)
        } catch (e: IOException) {
            println(e)
        }
        return null
    }


}
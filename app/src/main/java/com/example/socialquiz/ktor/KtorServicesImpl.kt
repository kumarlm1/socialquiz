package com.example.socialquiz.ktor

import com.example.socialquiz.responseItems.LoginPostData
import com.example.socialquiz.responseItems.LoginResponse
import com.example.socialquiz.responseItems.UserOrgs
import com.example.socialquiz.responseItems.UserOrgsItem
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonObject
import org.json.JSONObject

object KtorServicesImpl : KtorServices{

    private val httpclient by lazy { KtorClient.getInstance }

    const val BASE_URL = "http://192.168.43.105:3000"

    override suspend fun login(email: String, password: String): LoginResponse {
        return httpclient.post("$BASE_URL/auth/login"){
            contentType(ContentType.Application.Json)
            //JSONObject().put("email",email).put("password",password)
            body = LoginPostData(email, password)
        }
    }

    override suspend fun getOrgs(auth: String): List<UserOrgsItem> {
        return httpclient.get("$BASE_URL/user/organizations"){
            header("Authorization","Bearer $auth")
        }
    }
}
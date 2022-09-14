package com.example.socialquiz.responseItems

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer


@Serializable
data class LoginResponse(
    val access_token: String
)

@Serializable
data class LoginPostData(
    @SerialName("email")
    val email : String,
    @SerialName("password")
    val password : String
)
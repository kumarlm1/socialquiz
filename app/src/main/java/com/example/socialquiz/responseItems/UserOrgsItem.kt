package com.example.socialquiz.responseItems

import kotlinx.serialization.Serializable

@Serializable
data class UserOrgsItem(
    val joined: String,
    val org: Org,
    val role: String
)
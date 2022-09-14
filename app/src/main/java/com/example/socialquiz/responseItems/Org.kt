package com.example.socialquiz.responseItems

import kotlinx.serialization.Serializable


@Serializable
data class Org(
    val description: String,
    val id: String,
    val name: String,
    val visibility: String
)
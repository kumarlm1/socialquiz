package com.example.socialquiz.ktor

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

object KtorClient {
    private const val AUTHORIZATION = "Authorization"
    private var ACCESS_TOKEN = "Bearer"

    private val client = HttpClient(Android){
        expectSuccess = true
/*        defaultRequest {
                header(AUTHORIZATION, ACCESS_TOKEN)
        }*/
        install(JsonFeature){
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                }
            )
        }
        install(Logging){
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }
    }
    val getInstance = client
}
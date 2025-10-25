package com.example.sdui.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.serialization.kotlinx.json.json

object HuggingFaceApi {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) { json() }
    }

    private const val MODEL_URL = "https://api-inference.huggingface.co/models/your-model"
    private const val TOKEN = "hf_your_token"

    suspend fun getUIFromPrompt(prompt: String): String {
        val response: Map<String, Any> = client.post(MODEL_URL) {
            headers { append("Authorization", "Bearer $TOKEN") }
            setBody(mapOf("inputs" to prompt))
        }.body()
        return response["generated_text"] as? String ?: "{}"
    }
}

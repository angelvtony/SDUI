package com.example.sdui.data.repository

import com.example.sdui.data.remote.HuggingFaceApi

class SDUIRepository {
    suspend fun generateUI(prompt: String): String {
        return HuggingFaceApi.getUIFromPrompt(prompt)
    }
}
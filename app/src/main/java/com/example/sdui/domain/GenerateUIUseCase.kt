package com.example.sdui.domain

import com.example.sdui.data.repository.SDUIRepository

class GenerateUIUseCase(private val repository: SDUIRepository) {
    suspend operator fun invoke(prompt: String): String {
        return repository.generateUI(prompt)
    }
}
package com.example.sdui.ui.screen

data class MainUiState(
    val prompt: String = "",
    val generatedUIJson: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

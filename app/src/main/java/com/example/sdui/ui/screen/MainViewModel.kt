package com.example.sdui.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdui.domain.GenerateUIUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val generateUIUseCase: GenerateUIUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MainUiState())
        private set

    fun onPromptChange(newPrompt: String) {
        uiState = uiState.copy(prompt = newPrompt)
    }

    fun generateUI() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)
            try {
                val result = generateUIUseCase(uiState.prompt)
                uiState = uiState.copy(
                    generatedUIJson = result,
                    isLoading = false
                )
            } catch (e: Exception) {
                uiState = uiState.copy(isLoading = false, error = e.message)
            }
        }
    }
}

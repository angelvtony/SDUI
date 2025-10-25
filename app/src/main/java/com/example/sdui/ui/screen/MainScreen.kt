package com.example.sdui.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sdui.data.repository.SDUIRepository
import com.example.sdui.domain.GenerateUIUseCase
import com.example.sdui.ui.component.RenderDynamicUI

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val viewModel: MainViewModel = viewModel(
        factory = MainViewModelFactory()
    )
    val state = viewModel.uiState

    Column(Modifier.padding(16.dp)) {
        TextField(
            value = state.prompt,
            onValueChange = viewModel::onPromptChange,
            label = { Text("Enter prompt") }
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = { viewModel.generateUI() }) {
            Text("Generate UI")
        }

        Spacer(Modifier.height(16.dp))

        when {
            state.isLoading -> CircularProgressIndicator()
            state.error != null -> Text("Error: ${state.error}")
            state.generatedUIJson != null -> RenderDynamicUI(state.generatedUIJson)
        }
    }
}

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = SDUIRepository()
        val useCase = GenerateUIUseCase(repository)
        return MainViewModel(useCase) as T
    }
}


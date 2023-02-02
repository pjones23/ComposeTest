package com.example.app.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

interface ViewModel {
    fun processAction(action: Action)
    @Composable
    fun watchUiState(): State<UiState>
}
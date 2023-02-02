package com.example.app.ui

import com.example.app.network.RickAndMortyCharacter
import com.example.app.ui.base.UiState
import com.example.testcompose.R

data class CharacterUiState(
    val characters: List<RickAndMortyCharacter> = emptyList(),
    val showLoadingButton: Boolean = true,
    val loadingButtonText: Int = R.string.load,
    val loadingProgressText: Int? = null
) : UiState
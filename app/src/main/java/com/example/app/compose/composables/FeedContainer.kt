package com.example.app.compose.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.compose.Feed
import com.example.app.ui.CharacterUiState
import com.example.app.ui.CharacterViewModel
import com.example.app.ui.GetCharacters

@Composable
fun FeedContainer(
    modifier: Modifier = Modifier,
    vm: CharacterViewModel = viewModel()
) {
    val state: CharacterUiState by vm.watchUiState()
    val characters = remember(state) { state.characters }
    val showLoadButton = remember(state) { state.showLoadingButton }
    val progressText = remember(state) { state.loadingProgressText }
    val buttonText = remember(state) { state.loadingButtonText }
    Feed(
        modifier = modifier,
        posts = characters,
        showLoadingButton = showLoadButton,
        loadingProgressText = progressText,
        loadButtonText = buttonText,
        loadButtonClick = {
            vm.processAction(GetCharacters)
        }
    )
}

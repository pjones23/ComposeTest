package com.example.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.network.CharacterRepo
import com.example.app.network.RickAndMortyCharacter
import com.example.app.ui.base.Action
import com.example.testcompose.R
import org.koin.java.KoinJavaComponent.inject
import com.example.app.ui.base.ViewModel as VM

class CharacterViewModel: VM, ViewModel() {

    private val repo: CharacterRepo by inject(CharacterRepo::class.java)
    private var _uiState = CharacterUiState()
    private val stateLiveData = MutableLiveData<CharacterUiState>()

    override fun processAction(action: Action) {
        when(action) {
            is GetCharacters -> {
                getCharacters()
            }
        }
    }

    /**
     * IMPORTANT!!!
     * Return value MUST be State<CharacterUiState>, Not State<UiState>
     * Otherwise, FeedContainer will not work with watchUiState due to no getValue error
     */
    @Composable
    override fun watchUiState(): State<CharacterUiState> {
        return stateLiveData.observeAsState(initial = _uiState)
    }

    private fun getCharacters() {
        updateLoadingState()
        repo.getCharacters(object : CharacterRepo.CharacterRepoNotifier {
            override fun handleSuccess(response: List<RickAndMortyCharacter>?) {
                response?.takeIf { it.isNotEmpty() }?.let {
                    updateCharacters(it)
                }
            }

            override fun handleFailure() {
                notifyFail()
            }

        })
    }

    private fun updateLoadingState() {
        _uiState = _uiState.copy(
            loadingProgressText = R.string.loading,
            showLoadingButton = false
        )
        stateLiveData.postValue(_uiState)
    }

    private fun updateCharacters(newCharacters: List<RickAndMortyCharacter>) {
        val updated = _uiState.characters.toMutableList()
        updated.addAll(newCharacters)
        _uiState = _uiState.copy(
            characters = updated,
            loadingProgressText = null,
            loadingButtonText = R.string.load_more,
            showLoadingButton = true
        )
        stateLiveData.postValue(_uiState)
    }

    private fun notifyFail() {
        _uiState = _uiState.copy(
            loadingProgressText = R.string.fail,
            showLoadingButton = true
        )
        stateLiveData.postValue(_uiState)
    }
}
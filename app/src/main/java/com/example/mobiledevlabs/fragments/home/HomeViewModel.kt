package com.example.mobiledevlabs.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiledevlabs.data.CharacterRepository
import com.example.mobiledevlabs.data.model.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    val characters: StateFlow<List<Character>> =
        repository.observeCharacters()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private var currentFrom = 1201
    private var currentTo = 1250
    private val pageSize = 10

    init {
        coldStart()
    }

    private fun coldStart() {
        viewModelScope.launch {
            try {
                _uiState.value = HomeUiState.Loading
                repository.ensureCharactersLoaded(currentFrom, currentTo)
                _uiState.value = HomeUiState.Content
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Ошибка загрузки")
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            try {
                _uiState.value = HomeUiState.Loading
                repository.refresh(currentFrom, currentTo)
                _uiState.value = HomeUiState.Content
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Ошибка обновления")
            }
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            try {
                val nextFrom = currentTo + 1
                val nextTo = currentTo + pageSize

                repository.loadMore(nextFrom, nextTo)

                currentFrom = 1201
                currentTo = nextTo
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Ошибка дозагрузки")
            }
        }
    }
}

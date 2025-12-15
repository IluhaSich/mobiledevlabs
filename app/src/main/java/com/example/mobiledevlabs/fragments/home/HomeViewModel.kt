package com.example.mobiledevlabs.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiledevlabs.data.CharacterRepository
import com.example.mobiledevlabs.data.model.Character
import com.example.mobiledevlabs.data.network.HttpClientProvider
import com.example.mobiledevlabs.fragments.home.HomeUiState
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

class HomeViewModel : ViewModel() {

    private val client = HttpClientProvider.client
    private val repository = CharacterRepository(client)

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        _uiState.value = HomeUiState.Loading
        viewModelScope.launch {
            try {
                val characters = repository.getCharacters(1201, 1250)
                _uiState.value = HomeUiState.Success(characters)
            } catch (e: IOException) {
                _uiState.value = HomeUiState.Error.NoInternet
            } catch (e: ServerResponseException) {
                _uiState.value = HomeUiState.Error.Server
            } catch (e: ClientRequestException) {
                _uiState.value = HomeUiState.Error.Server
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error.Unknown(e.message ?: "Unknown error")
            }
        }
    }
}

package com.example.mobiledevlabs.fragments.home

import com.example.mobiledevlabs.data.model.Character

sealed interface HomeUiState {
    object Loading : HomeUiState

    data class Success(
        val characters: List<Character>
    ) : HomeUiState

    sealed interface Error : HomeUiState {
        object NoInternet : Error
        object Server : Error
        data class Unknown(val message: String) : Error
    }
}

package com.example.mobiledevlabs.ui.fragments.home

sealed interface HomeUiState {
    object Loading : HomeUiState
    object Content : HomeUiState
    data class Error(val message: String) : HomeUiState
}

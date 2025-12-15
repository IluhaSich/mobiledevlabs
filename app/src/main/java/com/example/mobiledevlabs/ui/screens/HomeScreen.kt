package com.example.mobiledevlabs.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mobiledevlabs.core_ui.*
import com.example.mobiledevlabs.fragments.home.HomeUiState
import com.example.mobiledevlabs.ui.components.CharacterCard
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme
import com.example.mobiledevlabs.ui.fragments.home.HomeViewModel

@Composable
internal fun HomeScreen(
    onSettingsClick: () -> Unit = {},
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current
    val uiState by viewModel.uiState.collectAsState()

    BaseScreen(modifier = modifier) {
        val colors = MaterialTheme.colorScheme
        Text(text = "Главный экран", style = MaterialTheme.typography.headlineMedium)

        VerticalSpacer(dimensions.paddings.paddingXL)

        when (uiState) {
            is HomeUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = colors.onBackground)
                }
            }

            is HomeUiState.Success -> {
                val characters = (uiState as HomeUiState.Success).characters
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(vertical = dimensions.paddings.paddingM)
                ) {
                    itemsIndexed(characters) { index, character ->
                        CharacterCard(character)
                        VerticalSpacer(dimensions.paddings.paddingM)
                    }
                }
            }


            is HomeUiState.Error -> {
                val message = when (uiState) {
                    HomeUiState.Error.NoInternet -> "Нет интернет-соединения"
                    HomeUiState.Error.Server -> "Ошибка сервера"
                    is HomeUiState.Error.Unknown -> (uiState as HomeUiState.Error.Unknown).message
                    else -> "Неизвестная ошибка"
                }

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = message, color = Color.Red)

                        VerticalSpacer(dimensions.paddings.paddingM)

                        Button(onClick = { viewModel.loadCharacters() }) {
                            Text("Повторить загрузку")
                        }
                    }
                }
            }
        }

        Button(onClick = onSettingsClick) {
            Text("Настройки")
        }


    }
}
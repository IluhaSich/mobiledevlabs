package com.example.mobiledevlabs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mobiledevlabs.core_ui.BaseScreen
import com.example.mobiledevlabs.core_ui.VerticalSpacer
import com.example.mobiledevlabs.ui.components.CharacterCard
import com.example.mobiledevlabs.ui.fragments.home.HomeUiState
import com.example.mobiledevlabs.ui.fragments.home.HomeViewModel

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel,
    onSettingsClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val characters by viewModel.characters.collectAsState()

    BaseScreen(modifier = modifier) {

        Text(
            text = "Главный экран",
            style = MaterialTheme.typography.headlineMedium
        )

        VerticalSpacer(16.dp)

        when (uiState) {

            HomeUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            HomeUiState.Content -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    itemsIndexed(characters) { _, character ->
                        CharacterCard(character)
                        VerticalSpacer(8.dp)
                    }

                    item {
                        VerticalSpacer(16.dp)
                        Button(
                            onClick = { viewModel.loadMore() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Text("Загрузить ещё")
                        }
                        VerticalSpacer(16.dp)
                    }
                }

                Button(
                    onClick = { viewModel.refresh() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Обновить")
                }
            }


            is HomeUiState.Error -> {
                val message = (uiState as HomeUiState.Error).message

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = message, color = Color.Red)
                        VerticalSpacer(16.dp)
                        Button(onClick = { viewModel.refresh() }) {
                            Text("Повторить")
                        }
                    }
                }
            }
        }

        VerticalSpacer(16.dp)

        Button(
            onClick = onSettingsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Настройки")
        }
    }
}

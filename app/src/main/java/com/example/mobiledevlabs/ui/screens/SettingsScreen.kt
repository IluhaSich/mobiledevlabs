package com.example.mobiledevlabs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobiledevlabs.core_ui.BaseScreen
import com.example.mobiledevlabs.fragments.setting.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit
) {
    val darkTheme by viewModel.darkTheme.collectAsState()

    BaseScreen {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

            Text("Настройки", style = MaterialTheme.typography.headlineMedium)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Темная тема")
                Spacer(Modifier.width(8.dp))
                Switch(
                    checked = darkTheme,
                    onCheckedChange = viewModel::setDarkTheme
                )
            }

            OutlinedTextField(
                value = viewModel.email.value,
                onValueChange = viewModel::saveEmail,
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))
            Text("Резервное копирование", style = MaterialTheme.typography.titleMedium)
            Text(viewModel.backupInfo.value)

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = viewModel::createBackup,
                enabled = !viewModel.backupExists.value && !viewModel.isBackupLoading.value,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (viewModel.isBackupLoading.value) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Создать резервную копию")
                }
            }

            Button(
                onClick = viewModel::deleteBackup,
                enabled = viewModel.backupExists.value,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Удалить файл")
            }

            Button(
                onClick = viewModel::restoreBackup,
                enabled = viewModel.internalBackupExists.value && !viewModel.backupExists.value,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Восстановить")
            }

            Spacer(Modifier.height(16.dp))
            Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text("Назад")
            }
        }
    }
}

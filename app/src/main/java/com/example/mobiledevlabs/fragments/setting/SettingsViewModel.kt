package com.example.mobiledevlabs.fragments.setting

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiledevlabs.data.CharacterRepository
import com.example.mobiledevlabs.data.SettingsRepository
import com.example.mobiledevlabs.data.backup.BackupUtils
import com.example.mobiledevlabs.data.backup.toBackupText
import com.example.mobiledevlabs.data.network.HttpClientProvider
import com.example.mobiledevlabs.preferences.UserPreferences
import com.example.mobiledevlabs.data.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = SettingsRepository(application)
    private val prefs = UserPreferences(application)
    private val characterRepo = CharacterRepository(HttpClientProvider.client, getApplication())


    val darkTheme = repo.darkThemeFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        false
    )

    val email = mutableStateOf(prefs.getEmail())

    val backupInfo = mutableStateOf("")
    val backupExists = mutableStateOf(false)
    val internalBackupExists = mutableStateOf(false)

    // состояние загрузки для UI
    val isBackupLoading = mutableStateOf(false)

    fun setDarkTheme(enabled: Boolean) {
        viewModelScope.launch {
            repo.setDarkTheme(enabled)
        }
    }

    fun saveEmail(value: String) {
        email.value = value
        prefs.saveEmail(value)
    }

    fun createBackup() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isBackupLoading.value = true

                val characters: List<Character> = characterRepo.loadFromApi(1201, 1250)

                val backupText = characters.toBackupText()

                BackupUtils.writeExternal(backupText)

                val info = BackupUtils.externalInfo()

                withContext(Dispatchers.Main) {
                    backupInfo.value = info
                    backupExists.value = true
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                withContext(Dispatchers.Main) {
                    isBackupLoading.value = false
                }
            }
        }
    }

    fun deleteBackup() {
        viewModelScope.launch(Dispatchers.IO) {
            BackupUtils.deleteExternal(getApplication())
            refreshState()
        }
    }

    fun restoreBackup() {
        viewModelScope.launch(Dispatchers.IO) {
            BackupUtils.restoreExternal(getApplication())
            refreshState()
        }
    }

    fun refreshState() {
        viewModelScope.launch(Dispatchers.IO) {
            val extExists = BackupUtils.externalFile().exists()
            val intExists = BackupUtils.internalFile(getApplication()).exists()
            val info = BackupUtils.externalInfo()
            withContext(Dispatchers.Main) {
                backupExists.value = extExists
                internalBackupExists.value = intExists
                backupInfo.value = info
            }
        }
    }
}

package com.example.mobiledevlabs.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map

class SettingsRepository(private val context: Context) {

    private val dataStore = context.settingsDataStore

    val darkThemeFlow = dataStore.data.map {
        it[SettingsKeys.DARK_THEME] ?: false
    }

    suspend fun setDarkTheme(enabled: Boolean) {
        dataStore.edit {
            it[SettingsKeys.DARK_THEME] = enabled
        }
    }

    val notificationsFlow = dataStore.data.map {
        it[SettingsKeys.NOTIFICATIONS] ?: true
    }

    suspend fun setNotifications(enabled: Boolean) {
        dataStore.edit {
            it[SettingsKeys.NOTIFICATIONS] = enabled
        }
    }
}

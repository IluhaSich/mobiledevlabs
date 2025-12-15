package com.example.mobiledevlabs.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.*

val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "settings"
)

object SettingsKeys {
    val DARK_THEME = booleanPreferencesKey("dark_theme")
    val NOTIFICATIONS = booleanPreferencesKey("notifications")
    val FONT_SIZE = floatPreferencesKey("font_size")
}

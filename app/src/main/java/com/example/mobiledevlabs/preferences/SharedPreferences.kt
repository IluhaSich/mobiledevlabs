package com.example.mobiledevlabs.preferences

import android.content.Context

class UserPreferences(context: Context) {

    private val prefs = context.getSharedPreferences(
        "user_prefs",
        Context.MODE_PRIVATE
    )

    fun saveEmail(email: String) {
        prefs.edit().putString("email", email).apply()
    }

    fun getEmail(): String =
        prefs.getString("email", "") ?: ""

    fun saveBackupFileName(name: String) {
        prefs.edit().putString("backup_name", name).apply()
    }

    fun getBackupFileName(): String =
        prefs.getString("backup_name", "characters_backup.txt") ?: ""
}

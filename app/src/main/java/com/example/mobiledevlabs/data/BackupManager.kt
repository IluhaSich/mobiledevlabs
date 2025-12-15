package com.example.mobiledevlabs.data

import android.content.Context
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BackupManager(private val context: Context) {

    private val fileName = "backup_student_01.txt"

    private val externalFile: File
        get() = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            ),
            fileName
        )

    private val internalBackup: File
        get() = File(context.filesDir, fileName)

    fun externalFileExists(): Boolean = externalFile.exists()

    fun internalBackupExists(): Boolean = internalBackup.exists()

    fun createBackup(data: String) {
        externalFile.writeText(data)
    }

    fun deleteExternalFileWithInternalCopy() {
        if (externalFile.exists()) {
            externalFile.copyTo(internalBackup, overwrite = true)
            externalFile.delete()
        }
    }

    fun restoreFromInternalBackup() {
        if (internalBackup.exists()) {
            internalBackup.copyTo(externalFile, overwrite = true)
        }
    }

    fun getExternalFileInfo(): String {
        if (!externalFile.exists()) return "Файл отсутствует"

        val sizeKb = externalFile.length() / 1024
        val date = SimpleDateFormat(
            "dd.MM.yyyy HH:mm",
            Locale.getDefault()
        ).format(Date(externalFile.lastModified()))

        return """
            Имя: ${externalFile.name}
            Путь: ${externalFile.absolutePath}
            Размер: ${sizeKb} КБ
            Дата: $date
        """.trimIndent()
    }
}

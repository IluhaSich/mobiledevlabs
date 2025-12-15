package com.example.mobiledevlabs.data.backup

import android.content.Context
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object BackupUtils {

    private const val FILE_NAME = "backup_characters.txt"

    fun externalFile(): File =
        File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS
            ),
            FILE_NAME
        )

    fun internalFile(context: Context): File =
        File(context.filesDir, FILE_NAME)

    fun writeExternal(content: String) {
        val file = externalFile()
        file.parentFile?.mkdirs()
        file.writeText(content)
    }

    fun deleteExternal(context: Context) {
        val external = externalFile()
        if (!external.exists()) return

        val internal = internalFile(context)
        external.copyTo(internal, overwrite = true)
        external.delete()
    }

    fun restoreExternal(context: Context) {
        val internal = internalFile(context)
        if (!internal.exists()) return

        val external = externalFile()
        external.parentFile?.mkdirs()
        internal.copyTo(external, overwrite = true)
        internal.delete()
    }

    fun externalInfo(): String {
        val file = externalFile()
        if (!file.exists()) return "Файл резервной копии отсутствует"

        val date = SimpleDateFormat(
            "dd.MM.yyyy HH:mm",
            Locale.getDefault()
        ).format(Date(file.lastModified()))

        return """
            Файл: ${file.name}
            Путь: ${file.absolutePath}
            Размер: ${file.length()} байт
            Изменен: $date
        """.trimIndent()
    }
}

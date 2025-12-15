package com.example.mobiledevlabs.data.backup

import com.example.mobiledevlabs.data.model.Character

fun List<Character>.toBackupText(): String =
    joinToString("\n\n") {
        """
        Имя: ${it.name}
        Культура: ${it.culture ?: "Unknown"}
        Рождение: ${it.born ?: "Unknown"}
        Титулы: ${it.titles.joinToString(", ")}
        Псевдонимы: ${it.aliases.joinToString(", ")}
        Актер: ${it.playedBy.joinToString(", ")}
        """.trimIndent()
    }

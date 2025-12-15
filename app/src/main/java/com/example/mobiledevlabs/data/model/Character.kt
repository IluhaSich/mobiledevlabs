package com.example.mobiledevlabs.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val name: String,
    val culture: String?,
    val born: String?,
    val titles: List<String>,
    val aliases: List<String>,
    val playedBy: List<String>
) : Parcelable



fun CharacterApi.toModel(): Character {
    return Character(
        name = name?.ifBlank { "Unknown" } ?: "Unknown",
        culture = culture?.ifBlank { "Unknown" },
        born = born?.ifBlank { "Unknown" },

        titles = titles
            ?.filter { it.isNotBlank() }
            ?: emptyList(),

        aliases = aliases
            ?.filter { it.isNotBlank() }
            ?: emptyList(),

        playedBy = playedBy
            ?.filter { it.isNotBlank() }
            ?: emptyList()
    )
}



fun List<CharacterApi>.toModels(): List<Character> = mapNotNull { it.toModel() }


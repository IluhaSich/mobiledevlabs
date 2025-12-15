package com.example.mobiledevlabs.data.model

data class Character(
    val name: String,
    val culture: String,
    val born: String,
    val titles: String,
    val aliases: String,
    val playedBy: String
)


fun CharacterApi.toModel(): Character {
    return Character(
        name = name?.ifBlank { "Unknown" } ?: "Unknown",
        culture = culture?.ifBlank { "Unknown" } ?: "Unknown",
        born = born?.ifBlank { "Unknown" } ?: "Unknown",
        titles = titles?.filter { it.isNotBlank() }?.joinToString(", ") ?: "Unknown",
        aliases = aliases?.filter { it.isNotBlank() }?.joinToString(", ") ?: "Unknown",
        playedBy = playedBy?.filter { it.isNotBlank() }?.joinToString(", ") ?: "Unknown"
    )
}


fun List<CharacterApi>.toModels(): List<Character> = mapNotNull { it.toModel() }


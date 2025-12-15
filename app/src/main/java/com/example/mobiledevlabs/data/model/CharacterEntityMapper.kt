package com.example.mobiledevlabs.data.model

import com.example.mobiledevlabs.data.local.CharacterEntity

fun CharacterEntity.toModel(): Character =
    Character(
        name = name,
        culture = culture,
        born = born,
        titles = if (titles.isBlank()) emptyList() else titles.split("|"),
        aliases = if (aliases.isBlank()) emptyList() else aliases.split("|"),
        playedBy = if (playedBy.isBlank()) emptyList() else playedBy.split("|")
    )

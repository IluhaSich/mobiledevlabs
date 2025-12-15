package com.example.mobiledevlabs.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterApi(
    val name: String? = null,
    val culture: String? = null,
    val born: String? = null,
    val titles: List<String>? = null,
    val aliases: List<String>? = null,
    @SerialName("playedBy")
    val playedBy: List<String>? = null
)

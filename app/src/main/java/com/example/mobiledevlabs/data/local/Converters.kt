package com.example.mobiledevlabs.data.local

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromString(value: String): List<String> =
        if (value.isBlank()) emptyList() else value.split("|")

    @TypeConverter
    fun toString(list: List<String>): String =
        list.joinToString("|")
}

package com.example.mobiledevlabs

internal data class User(
    val name: String,
    val surname: String,
    val email: String,
    val password: String
) : java.io.Serializable
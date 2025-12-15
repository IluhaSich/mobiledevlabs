package com.example.mobiledevlabs.data.model

import java.io.Serializable

data class User(
    val name: String,
    val surname: String,
    val email: String,
    val password: String
) : Serializable
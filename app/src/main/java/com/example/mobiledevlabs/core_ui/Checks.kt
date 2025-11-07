package com.example.mobiledevlabs.core_ui

import android.util.Patterns

fun isEmailValid(email: String): Boolean {
    return (email.isBlank() || email.length < 4)
}

fun returnEmailError(email: String): String? {
    return if (email.isBlank() ) "Почта не может быть пустой"
    else if ( email.length < 4) "Почта содержит больше 3 символов"
    else null
}

fun returnPasswordError(password: String): String? {
    return if (password.isBlank() ) "Пароль не может быть пустым"
    else if( password.length < 4) "Пароль содержит больше 3 символов"
    else null
}
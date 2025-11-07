package com.example.mobiledevlabs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.mobiledevlabs.User
import com.example.mobiledevlabs.core_ui.*
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme

@Composable
internal fun SignUpScreen(
    onSignUp: (String, String, String, String) -> Unit,
    onSignUpWithObject: (User) -> Unit,
    onLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current
    val outlinedTextFieldShape = RoundedCornerShape(dimensions.cornerShapes.shapeXL)
    val buttonShape = RoundedCornerShape(dimensions.cornerShapes.shapeS)

    val subTitleStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
    val errorColor = Colors.Error
    val errorStyle = MaterialTheme.typography.bodySmall

    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var firstNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var lastNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var emailError by rememberSaveable { mutableStateOf<String?>(null) }
    var passwordError by rememberSaveable { mutableStateOf<String?>(null) }
    var confirmPasswordError by rememberSaveable { mutableStateOf<String?>(null) }

    BaseScreen {

        Text(text = "Регистрация", style = MaterialTheme.typography.headlineMedium)

//        VerticalSpacer(dimensions.paddings.paddingXL)

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Личные данные",
            style = subTitleStyle
        )

//        VerticalSpacer(dimensions.paddings.paddingM)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = firstName,
            onValueChange = { firstName = it; firstNameError = null },
            placeholder = { Text("Имя") },
            isError = firstNameError != null,
            shape = outlinedTextFieldShape,
            singleLine = true
        )
        firstNameError?.let { Text(it, color = errorColor, style = errorStyle) }

//        VerticalSpacer(dimensions.paddings.paddingM)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = lastName,
            onValueChange = { lastName = it; lastNameError = null },
            placeholder = { Text("Фамилия") },
            isError = lastNameError != null,
            shape = outlinedTextFieldShape,
            singleLine = true
        )
        lastNameError?.let { Text(it, color = errorColor, style = errorStyle) }

//        VerticalSpacer(dimensions.paddings.paddingXL)

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Данные аккаунта",
            style = subTitleStyle
        )

//        VerticalSpacer(dimensions.paddings.paddingM)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it; emailError = null },
            placeholder = { Text("Почта") },
            isError = emailError != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = outlinedTextFieldShape,
            singleLine = true
        )
        emailError?.let { Text(it, color = errorColor, style = errorStyle) }

//        VerticalSpacer(dimensions.paddings.paddingM)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it; passwordError = null },
            placeholder = { Text("Пароль") },
            isError = passwordError != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            shape = outlinedTextFieldShape,
            singleLine = true
        )
        passwordError?.let { Text(it, color = errorColor, style = errorStyle) }

//        VerticalSpacer(dimensions.paddings.paddingM)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = confirmPassword,
            onValueChange = { confirmPassword = it; confirmPasswordError = null },
            placeholder = { Text("Подтвердите пароль") },
            isError = confirmPasswordError != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            shape = outlinedTextFieldShape,
            singleLine = true
        )
        confirmPasswordError?.let { Text(it, color = errorColor, style = errorStyle) }

//        VerticalSpacer(dimensions.paddings.paddingXL)

        Button(
            modifier = Modifier.defaultMinSize(
                minWidth = dimensions.buttonDefaults.defaultWidth,
                minHeight = dimensions.buttonDefaults.defaultHeight
            ),
            shape = buttonShape,
            onClick = {
                firstNameError = if (firstName.isBlank()) "Имя не должно быть пустым" else null
                lastNameError = if (lastName.isBlank()) "Фамилия не должна быть пустой" else null
                emailError = returnEmailError(email)
                passwordError = returnPasswordError(password)
                confirmPasswordError =
                    if (password != confirmPassword) "Пароли не совпадают" else null

                val hasError = listOf(
                    firstNameError,
                    lastNameError,
                    emailError,
                    passwordError,
                    confirmPasswordError
                )
                    .any { it != null }
                if (!hasError) {
                    onSignUp(firstName, lastName, email, password)
                }
            }
        ) {
            Text("Зарегистрироваться")
        }

//        VerticalSpacer(dimensions.paddings.paddingS)

        Button(
            modifier = Modifier.defaultMinSize(
                minWidth = dimensions.buttonDefaults.defaultWidth,
                minHeight = dimensions.buttonDefaults.defaultHeight
            ),
            shape = buttonShape,
            onClick = {
                firstNameError = if (firstName.isBlank()) "Имя не должно быть пустым" else null
                lastNameError = if (lastName.isBlank()) "Фамилия не должна быть пустой" else null
                emailError = returnEmailError(email)
                passwordError = returnPasswordError(password)
                confirmPasswordError =
                    if (password != confirmPassword) "Пароли не совпадают" else null

                val hasError = listOf(
                    firstNameError,
                    lastNameError,
                    emailError,
                    passwordError,
                    confirmPasswordError
                )
                    .any { it != null }
                if (!hasError) {
                    onSignUpWithObject(User(firstName, lastName, email, password))
                }
            }
        ) {
            Text("Зарегистрироваться через Object")
        }

//        VerticalSpacer(dimensions.paddings.paddingS)

        Button(
            modifier = Modifier.defaultMinSize(
                minWidth = dimensions.buttonDefaults.defaultWidth,
                minHeight = dimensions.buttonDefaults.defaultHeight
            ),
            shape = buttonShape,
            onClick = onLogin
        ) {
            Text("Авторизоваться")
        }

    }
}

@LightModePreview
@Composable
private fun SignUpScreenPreviewLight() {
    MobiledevlabsTheme(darkTheme = false) {
        SignUpScreen(
            onSignUp = { firstName, lastName, email, password ->  },
            onLogin = {},
            onSignUpWithObject = {}

        )
    }
}

@NightModePreview
@Composable
private fun SignUpScreenPreviewDark() {
    MobiledevlabsTheme(darkTheme = true) {
        SignUpScreen(
            onSignUp = { firstName, lastName, email, password ->  },
            onLogin = {},
            onSignUpWithObject = {}
        )
    }
}


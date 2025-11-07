package com.example.mobiledevlabs.ui.screens

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.mobiledevlabs.core_ui.BaseScreen
import com.example.mobiledevlabs.core_ui.Colors
import com.example.mobiledevlabs.core_ui.LightModePreview
import com.example.mobiledevlabs.core_ui.LocalDimensions
import com.example.mobiledevlabs.core_ui.NightModePreview
import com.example.mobiledevlabs.core_ui.VerticalSpacer
import com.example.mobiledevlabs.core_ui.isEmailValid
import com.example.mobiledevlabs.core_ui.returnEmailError
import com.example.mobiledevlabs.core_ui.returnPasswordError
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme

@Composable
fun SignInScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    onSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    val dimensions = LocalDimensions.current
    val outlinedTextFieldShape = RoundedCornerShape(dimensions.cornerShapes.shapeXL)
    val buttonShape = RoundedCornerShape(dimensions.cornerShapes.shapeS)

    val errorColor = Colors.Error
    val errorStyle = MaterialTheme.typography.bodySmall

    var emailError by rememberSaveable { mutableStateOf<String?>(null) }
    var passwordError by rememberSaveable { mutableStateOf<String?>(null) }
    BaseScreen {
        VerticalSpacer(dimensions.paddings.paddingS)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {
                onEmailChange(it)
                emailError = null
            },
            placeholder = { Text(text = "Почта") },
            isError = emailError != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            maxLines = 1,
            shape = outlinedTextFieldShape
        )
        emailError?.let { Text(text = it, color = errorColor, style = errorStyle) }

//        VerticalSpacer(dimensions.paddings.paddingM)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {
                onPasswordChange(it)
                passwordError = null
            },
            placeholder = { Text(text = "Пароль") },
            isError = passwordError != null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            maxLines = 1,
            shape = outlinedTextFieldShape
        )
        passwordError?.let { Text(text = it, color = errorColor, style = errorStyle) }

//        VerticalSpacer(dimensions.paddings.paddingXL)

        Button(
            modifier = Modifier
                .defaultMinSize(
                    minWidth = dimensions.buttonDefaults.defaultWidth,
                    minHeight = dimensions.buttonDefaults.defaultHeight
                ),
            shape = buttonShape,
            onClick = {
                emailError = returnEmailError(email)
                passwordError = returnPasswordError(password)

                val hasError = listOf(emailError, passwordError).any { it != null }
                if (!hasError) {
                    onLogin()
                }
            }
        ) {
            Text(text = "Войти")
        }

//        VerticalSpacer(dimensions.paddings.paddingS)

        Button(
            modifier = Modifier
                .defaultMinSize(
                    minWidth = dimensions.buttonDefaults.defaultWidth,
                    minHeight = dimensions.buttonDefaults.defaultHeight
                ),
            shape = buttonShape,
            onClick = onSignUp
        ) {
            Text(text = "Зарегистрироваться")
        }
    }

}

@LightModePreview
@Composable
private fun OnboardScreenPreviewLight() {
    MobiledevlabsTheme(darkTheme = false) {
        SignInScreen(
            onLogin = {},
            onSignUp = {},
            email = "",
            password = "",
            onEmailChange = {},
            onPasswordChange = {}
        )
    }
}

@NightModePreview
@Composable
private fun OnboardScreenPreviewDark() {
    MobiledevlabsTheme(darkTheme = true) {
        SignInScreen(
            onLogin = {},
            onSignUp = {},
            email = "",
            password = "",
            onEmailChange = {},
            onPasswordChange = {}
        )
    }
}


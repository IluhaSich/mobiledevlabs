package com.example.mobiledevlabs.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.mobiledevlabs.core_ui.Colors

private val DarkColorScheme = darkColorScheme(
    primary = Colors.TullyBlue,
    onPrimary = Colors.ButtonOnPrimary,
    secondary = Colors.LannisterGold,
    onSecondary = Colors.TextLight,
    background = Colors.DarkIron,
    onBackground = Colors.TextLight,
    surface = Colors.SurfaceDark,
    onSurface = Colors.TextLight,
    error = Colors.Error
)

private val LightColorScheme = lightColorScheme(
    primary = Colors.TargaryenRed,
    onPrimary = Colors.TextLight,
    secondary = Colors.TyrellGreen,
    onSecondary = Colors.TextDark,
    background = Colors.BackgroundLight,
    onBackground = Colors.TextDark,
    surface = Colors.SurfaceLight,
    onSurface = Colors.TextDark,
    error = Colors.Error
)

@Composable
fun MobiledevlabsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

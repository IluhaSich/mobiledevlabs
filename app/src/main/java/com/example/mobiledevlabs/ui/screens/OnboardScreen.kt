package com.example.mobiledevlabs.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mobiledevlabs.core_ui.BaseScreen
import com.example.mobiledevlabs.core_ui.LightModePreview
import com.example.mobiledevlabs.core_ui.NightModePreview
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme

@Composable
fun OnboardScreen(
    onNext: () -> Unit = {}
) {
    BaseScreen(
    ) {
        val colors = MaterialTheme.colorScheme

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "GoTInformator",
            style = MaterialTheme.typography.displayMedium,
            color = colors.primary,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Энциклопедия по вселенной Игры Престолов",
            style = MaterialTheme.typography.titleMedium,
            color = colors.onBackground,
            textAlign = TextAlign.Center
        )

        Button(
            onClick = onNext,
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Начать",
                color = colors.onPrimary
            )
        }

    }
}

@LightModePreview
@Composable
private fun OnboardScreenPreviewLight() {
    MobiledevlabsTheme(darkTheme = false) {
        OnboardScreen(
            onNext = {}
        )
    }
}

@NightModePreview
@Composable
private fun OnboardScreenPreviewDark() {
    MobiledevlabsTheme(darkTheme = true) {
        OnboardScreen(
            onNext = {}
        )
    }
}


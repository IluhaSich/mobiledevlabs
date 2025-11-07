package com.example.mobiledevlabs.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mobiledevlabs.core_ui.*
import com.example.mobiledevlabs.ui.components.CharacterCard
import com.example.mobiledevlabs.ui.theme.MobiledevlabsTheme

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val dimensions = LocalDimensions.current

    BaseScreen(modifier = modifier) {
        Text(text = "Главный экран", style = MaterialTheme.typography.headlineMedium)

        VerticalSpacer(dimensions.paddings.paddingXL)

        CharacterCard(
            name = "Jon Snow",
            gender = "Male",
            culture = "Northmen",
            born = "In 283 AC",
            onDetailed = {},
        )
        CharacterCard(
            name = "Jon Snow",
            gender = "Male",
            culture = "Northmen",
            born = "In 283 AC",
            onDetailed = {},
        )
        CharacterCard(
            name = "Jon Snow",
            gender = "Male",
            culture = "Northmen",
            born = "In 283 AC",
            onDetailed = {},
        )
        CharacterCard(
            name = "Jon Snow",
            gender = "Male",
            culture = "Northmen",
            born = "In 283 AC",
            onDetailed = {},
        )
        CharacterCard(
            name = "Jon Snow",
            gender = "Male",
            culture = "Northmen",
            born = "In 283 AC",
            onDetailed = {},
        )


    }
}


@LightModePreview
@Composable
private fun HomeScreenPreviewLight() {
    MobiledevlabsTheme(darkTheme = false) {
        HomeScreen()
    }
}

@NightModePreview
@Composable
private fun HomeScreenPreviewDark() {
    MobiledevlabsTheme(darkTheme = true) {
        HomeScreen()
    }
}
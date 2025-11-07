package com.example.mobiledevlabs.core_ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp


@Composable
fun VerticalSpacer(height: Dp) {
    val dimensions = LocalDimensions.current
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun HorizontalSpacer(width: Dp) {
    val dimensions = LocalDimensions.current
    Spacer(modifier = Modifier.width(width))
}
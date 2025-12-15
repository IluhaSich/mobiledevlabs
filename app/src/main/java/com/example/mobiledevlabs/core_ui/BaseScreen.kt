package com.example.mobiledevlabs.core_ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobiledevlabs.R

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    backgroundRes: Int = R.drawable.got_dr,
    backgroundAlpha: Float = 1.0f,
    content: @Composable ColumnScope.() -> Unit
) {
    val dimensions = LocalDimensions.current
    val systemPaddings = WindowInsets.displayCutout
        .union(WindowInsets.systemBars)
        .asPaddingValues()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = backgroundRes),
                contentScale = ContentScale.Crop,
                alpha = backgroundAlpha
            )
            .then(modifier),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            shape = RoundedCornerShape(
                topStart = dimensions.cornerShapes.shapeXL,
                topEnd = dimensions.cornerShapes.shapeXL
            ),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            shadowElevation = 8.dp,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .imePadding()
        ) {
            Column(
                modifier = Modifier
                    .padding(systemPaddings)
                    .padding(horizontal = dimensions.paddings.paddingM)
                    .padding(vertical = dimensions.paddings.paddingS), // убрали verticalScroll
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensions.paddings.paddingM),
                content = content
            )
        }

    }
}

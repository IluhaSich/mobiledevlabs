package com.example.mobiledevlabs.core_ui

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalDimensions = compositionLocalOf { Dimensions() }

data class Dimensions(
    val paddings: Paddings = Paddings(),
    val cornerShapes: CornerShapes = CornerShapes(),
    val buttonDefaults: ButtonDefaults = ButtonDefaults(),
    val imageDefaults: ImageDefaults = ImageDefaults(),
    val other: Other = Other()
)

data class Paddings(
    val paddingXS: Dp = 4.dp,
    val paddingS: Dp = 8.dp,
    val paddingM: Dp = 16.dp,
    val paddingXL: Dp = 32.dp,
    val paddingXXL: Dp = 64.dp,
)

data class CornerShapes(
    val shapeS: Dp = 12.dp,
    val shapeM: Dp = 16.dp,
    val shapeXL: Dp = 24.dp,
)

data class ButtonDefaults(
    val defaultHeight: Dp = 48.dp,
    val defaultWidth: Dp = 216.dp
)

data class ImageDefaults(
    val defaultCardSize: Dp = 96.dp
)

data class Other(
    val borderWidth: Dp = 1.dp
)
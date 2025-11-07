package com.example.mobiledevlabs.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobiledevlabs.core_ui.*
import androidx.compose.ui.unit.dp

@Composable
internal fun CharacterCard(
    name: String,
    gender: String,
    culture: String,
    born: String,
    onDetailed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimensions = LocalDimensions.current
    val buttonShape = RoundedCornerShape(dimensions.cornerShapes.shapeS)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensions.cornerShapes.shapeM))
            .border(
                width = dimensions.other.borderWidth,
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(dimensions.cornerShapes.shapeM)
            )
            .background(MaterialTheme.colorScheme.background)
            .padding(
                vertical = dimensions.paddings.paddingS,
                horizontal = dimensions.paddings.paddingM
            ),
        verticalArrangement = Arrangement.spacedBy(dimensions.paddings.paddingM),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = gender,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = culture,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = born,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            modifier = Modifier.defaultMinSize(
                minWidth = dimensions.buttonDefaults.defaultWidth,
                minHeight = dimensions.buttonDefaults.defaultHeight
            ),
            shape = buttonShape,
            onClick = onDetailed
        ) {
            Text("Посмотреть")
        }
    }
}

@Preview
@Composable
private fun CardPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(LocalDimensions.current.paddings.paddingM),
        verticalArrangement = Arrangement.spacedBy(LocalDimensions.current.paddings.paddingM)
    ) {
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
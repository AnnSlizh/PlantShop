package by.slizh.plantshop.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import by.slizh.plantshop.ui.theme.Black
import by.slizh.plantshop.ui.theme.Gray
import by.slizh.plantshop.ui.theme.White

@Composable
fun CustomFilterChip(
    collectionTitle: String,
    selectedChip: String?,
    onChipClick: (String) -> Unit,
    query: String
) {
    FilterChip(
        selected = selectedChip == collectionTitle || query == collectionTitle,
        onClick = { onChipClick(collectionTitle) },
        label = {
            Text(
                text = collectionTitle
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Gray,
            selectedContainerColor = Black,
            labelColor = Black,
            selectedLabelColor = White
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = selectedChip == collectionTitle || query == collectionTitle,
            borderColor = Black,
            selectedBorderColor = Color.Transparent
        ),
        modifier = Modifier.padding(end = 11.dp),
        shape = RoundedCornerShape(100.dp)
    )
}
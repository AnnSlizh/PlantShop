package by.slizh.plantshop.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import by.slizh.plantshop.ui.theme.DarkGray

@Composable
fun PlantParameter(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Text(text = value, color = DarkGray)
    }
}
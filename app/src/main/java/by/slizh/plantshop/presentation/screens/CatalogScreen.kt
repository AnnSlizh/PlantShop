package by.slizh.plantshop.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import by.slizh.plantshop.domain.Plant
import by.slizh.plantshop.domain.plants
import by.slizh.plantshop.presentation.components.CustomFilterChip
import by.slizh.plantshop.presentation.components.PlantCard
import by.slizh.plantshop.presentation.navigation.Screen
import by.slizh.plantshop.ui.theme.mulishFamily

@Composable
fun CatalogScreen(navController: NavController) {

    val titles = listOf("All", "Popular", "Indoor", "Outdoor")
    var selectedChip by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 16.dp, end = 15.dp)
    ) {

        Text(
            text = "Categories", fontSize = 25.sp, fontFamily = mulishFamily,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyRow {
            items(titles) { title ->
                CustomFilterChip(
                    collectionTitle = title,
                    selectedChip = selectedChip,
                    onChipClick = { chipTitle ->
                        selectedChip = chipTitle
                    },
                    query = ""
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
        ) {
            items(plants) { plant ->
                PlantCard(
                    plantName = plant.name,
                    plantPrice = plant.price,
                    showAddToCartButton = true,
                    showDetailsPlant = {
                        navController.navigate(
                            Screen.DetailsScreen.createRoute(
                                1
                            )
                        )
                    }
                )
            }
        }

    }

}

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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.slizh.plantshop.presentation.components.CustomFilterChip
import by.slizh.plantshop.presentation.components.cards.PlantCard
import by.slizh.plantshop.presentation.navigation.Screen
import by.slizh.plantshop.presentation.viewModels.authorization.AuthViewModel
import by.slizh.plantshop.presentation.viewModels.cart.CartEvent
import by.slizh.plantshop.presentation.viewModels.cart.CartViewModel
import by.slizh.plantshop.presentation.viewModels.plantsCatalog.PlantEvent
import by.slizh.plantshop.presentation.viewModels.plantsCatalog.PlantViewModel
import by.slizh.plantshop.ui.theme.Green
import by.slizh.plantshop.ui.theme.Red
import by.slizh.plantshop.ui.theme.mulishFamily

@Composable
fun CatalogScreen(
    navController: NavController,
    plantViewModel: PlantViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val titles = listOf("All", "Popular", "Indoor", "Outdoor")

    val plantState by plantViewModel.plantState.collectAsState()
    val userId = authViewModel.getCurrentUser()?.uid

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
                    selectedChip = plantState.selectedChip,
                    onChipClick = { chipTitle ->
                        plantViewModel.onEvent(PlantEvent.SelectChip(chipTitle))
                        plantViewModel.onEvent(PlantEvent.FilterPlants(chipTitle))
                    },
                    query = ""
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (plantState.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                color = Green
            )
        } else if (plantState.error != null) {
            Text(text = "No results found", color = Red)
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(plantState.filteredPlants) { plant ->
                    PlantCard(
                        plantName = plant.name,
                        plantPrice = "$" + plant.price,
                        showAddToCartButton = true,
                        plantPhoto = plant.photo,
                        showDetailsPlant = {
                            navController.navigate(
                                Screen.DetailsScreen.createRoute(
                                    plant.id
                                )
                            )
                        },
                        addToCart = {
                            if (userId != null) {
                                cartViewModel.onCartEvent(CartEvent.AddToCart(plant))
                            }
                        }
                    )
                }
            }
        }
    }
}

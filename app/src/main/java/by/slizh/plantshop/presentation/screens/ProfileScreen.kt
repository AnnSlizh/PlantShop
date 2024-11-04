package by.slizh.plantshop.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import by.slizh.plantshop.domain.plants
import by.slizh.plantshop.presentation.components.AccountCard
import by.slizh.plantshop.presentation.components.PlantCard
import by.slizh.plantshop.presentation.components.PlantItemCart
import by.slizh.plantshop.presentation.navigation.Screen
import by.slizh.plantshop.ui.theme.mulishFamily

@Composable
fun ProfileScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
        //  .padding(start = 20.dp, top = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "My Profile",
                fontSize = 18.sp,
                fontFamily = mulishFamily,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        AccountCard()

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "My purchases",
            fontSize = 20.sp,
            fontFamily = mulishFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 15.dp, end = 15.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))


        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp)
        ) {
            items(plants) { plant ->
                PlantCard(
                    plantName = plant.name,
                    plantPrice = plant.price,
                    showAddToCartButton = false,
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



package by.slizh.plantshop.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.slizh.plantshop.R
import by.slizh.plantshop.presentation.components.CustomTopAppBar
import by.slizh.plantshop.presentation.components.PlantParameter
import by.slizh.plantshop.presentation.viewModels.plantDetails.PlantDetailsEvent
import by.slizh.plantshop.presentation.viewModels.plantDetails.PlantDetailsViewModel
import by.slizh.plantshop.presentation.viewModels.plantsCatalog.PlantViewModel
import by.slizh.plantshop.ui.theme.Black
import by.slizh.plantshop.ui.theme.DarkGray
import by.slizh.plantshop.ui.theme.Gray
import by.slizh.plantshop.ui.theme.Green
import by.slizh.plantshop.ui.theme.LightGreen
import by.slizh.plantshop.ui.theme.White
import by.slizh.plantshop.ui.theme.Yellow
import by.slizh.plantshop.ui.theme.mulishFamily
import coil.compose.AsyncImage

@Composable
fun DetailsScreen(
    navController: NavController,
    plantId: Int?,
    plantDetailsViewModel: PlantDetailsViewModel = hiltViewModel()
) {
    val plantDetailState by plantDetailsViewModel.detailsState.collectAsState()

    LaunchedEffect(plantId) {
        if (plantId != null) {
            plantDetailsViewModel.onEvent(PlantDetailsEvent.LoadPlantById(plantId))
        }
    }
    val plant = plantDetailState.plant

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 16.dp, end = 15.dp)
    ) {
        CustomTopAppBar(navController = navController)

        Spacer(modifier = Modifier.height(20.dp))

        if (plantDetailState.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                color = Green
            )
        } else {
            if (plant != null) {
                var inCart by remember { mutableStateOf(false) }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f).background(Gray),
                ) {

                    AsyncImage(
                        model = plant.photo,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Fit,
                        placeholder = painterResource(id = R.drawable.placeholder)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = plant.name,
                        fontSize = 24.sp,
                        fontFamily = mulishFamily,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.star_icon),
                            contentDescription = null,
                            tint = Yellow,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = plant.rating,
                            fontFamily = mulishFamily,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = plant.description,
                    fontSize = 13.sp,
                    color = DarkGray,
                    fontFamily = mulishFamily,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PlantParameter(label = "Size", value = plant.size)
                    PlantParameter(label = "Category", value = plant.category)
                    PlantParameter(label = "Humidity", value = plant.humidity.toString() + "%")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$" + plant.price.toString(),
                        fontSize = 24.sp,
                        fontFamily = mulishFamily,
                        fontWeight = FontWeight.Bold
                    )
                    Button(
                        onClick = { inCart = !inCart },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Black,
                            contentColor = White
                        )
                    ) {
                        Text(
                            text = if (inCart) "Remove from Cart" else "Add to Cart",
                            fontFamily = mulishFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }

        }
    }
}

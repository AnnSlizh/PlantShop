package by.slizh.plantshop.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import by.slizh.plantshop.presentation.components.PlantItemCart
import by.slizh.plantshop.presentation.navigation.Screen
import by.slizh.plantshop.ui.theme.Black
import by.slizh.plantshop.ui.theme.White
import by.slizh.plantshop.ui.theme.mulishFamily

@Composable
fun CartScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 16.dp, end = 15.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "My Cart",
                fontSize = 18.sp,
                fontFamily = mulishFamily,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 490.dp)
        ) {
            items(5) { plant ->
                PlantItemCart(showDetailsPlant = {
                    navController.navigate(
                        Screen.DetailsScreen.createRoute(
                            1
                        )
                    )
                })
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        HorizontalDivider(
            thickness = 1.dp,
            color = Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total:",
                fontSize = 22.sp,
                fontFamily = mulishFamily,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = "$100", fontSize = 22.sp,
                fontFamily = mulishFamily,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ }, shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Black, contentColor = White)
            ) {
                Text(
                    text = "Place order",
                    fontSize = 18.sp,
                    fontFamily = mulishFamily,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

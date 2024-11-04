package by.slizh.plantshop.presentation.screens

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import by.slizh.plantshop.R
import by.slizh.plantshop.presentation.components.CustomTopAppBar
import by.slizh.plantshop.presentation.components.PlantParameter
import by.slizh.plantshop.ui.theme.DarkGray
import by.slizh.plantshop.ui.theme.Yellow
import by.slizh.plantshop.ui.theme.mulishFamily
import coil.compose.AsyncImage

@Composable
fun DetailsScreen(navController: NavController, plantId: Int?) {

    var inCart by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 16.dp, end = 15.dp)
    ) {
        CustomTopAppBar(navController = navController)

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            AsyncImage(
                model = R.drawable.img,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Fit,
                placeholder = painterResource(id = R.drawable.img)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Peace Lily Plant",
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
                    text = "4.8",
                    fontFamily = mulishFamily,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Peace Lily (Spathiphyllum spp.) is a popular houseplant known for its elegant, deep green leaves and white flowers. It is native to the tropical rainforests of Central and South America, where it grows as an understory plant, meaning it thrives in low light conditions.",
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
            PlantParameter(label = "Size", value = "Medium")
            PlantParameter(label = "Height", value = "3 Feet")
            PlantParameter(label = "Humidity", value = "50%")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            //      .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$34.00",
                fontSize = 24.sp,
                fontFamily = mulishFamily,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = { inCart = !inCart },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
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

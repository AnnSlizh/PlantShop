package by.slizh.plantshop.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.plantshop.R
import by.slizh.plantshop.ui.theme.Black
import by.slizh.plantshop.ui.theme.Gray
import by.slizh.plantshop.ui.theme.LightGreen
import by.slizh.plantshop.ui.theme.Red
import by.slizh.plantshop.ui.theme.White
import by.slizh.plantshop.ui.theme.Yellow
import by.slizh.plantshop.ui.theme.mulishFamily
import coil.compose.AsyncImage

@Composable
fun PlantItemCart(
    plantName: String,
    plantPrice: Int,
    plantPhoto: String,
    showDetailsPlant: () -> Unit,
    onRemoveFromCart: () -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(containerColor = Gray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AsyncImage(
                model = plantPhoto,
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                    .width(100.dp)
                    .height(140.dp)
                    .clickable { showDetailsPlant() },
                placeholder = painterResource(id = R.drawable.img),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 20.dp)
                    .weight(1f)
            ) {
                Text(
                    text = plantName,
                    fontSize = 20.sp,
                    color = Black,
                    fontFamily = mulishFamily,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "$$plantPrice",
                    fontSize = 18.sp,
                    color = Black,
                    fontFamily = mulishFamily,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            IconButton(
                onClick = { onRemoveFromCart() },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.cart_remove_icon),
                    contentDescription = "Remove from cart",
                    tint = Red
                )
            }
        }
    }
}


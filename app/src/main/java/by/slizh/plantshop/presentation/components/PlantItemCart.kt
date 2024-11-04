package by.slizh.plantshop.presentation.components

import androidx.compose.foundation.Image
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
import coil.compose.AsyncImage

@Composable
fun PlantItemCart() {

    Card(
        colors = CardDefaults.cardColors(containerColor = Gray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            // .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AsyncImage(
                model = R.drawable.img,
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                    .width(100.dp)
                    .height(140.dp),
                placeholder = painterResource(id = R.drawable.img),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 20.dp)
                    .weight(1f)
            ) {
                Text(text = "Peace Lily Plant", fontSize = 20.sp, color = Black)

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "$34.00",
                    fontSize = 18.sp,
                    color = Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            IconButton(
                onClick = {/*TODO*/ },
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

@Preview(showSystemUi = true)
@Composable
fun PlantItemCartPreview() {
    PlantItemCart()
}

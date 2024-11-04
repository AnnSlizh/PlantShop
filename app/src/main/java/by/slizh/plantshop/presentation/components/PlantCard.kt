package by.slizh.plantshop.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.plantshop.R
import by.slizh.plantshop.ui.theme.Black
import by.slizh.plantshop.ui.theme.LightGreen
import by.slizh.plantshop.ui.theme.White
import by.slizh.plantshop.ui.theme.mulishFamily
import coil.compose.AsyncImage


@Composable
fun PlantCard(
    plantName: String,
    plantPrice: String,
    showAddToCartButton: Boolean,
    showDetailsPlant: () -> Unit
) {
    var inCart by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LightGreen),
    ) {
        Column(
            modifier = Modifier
                .padding(13.dp)
        ) {

            Box(
                modifier = Modifier
                    .width(140.dp)
                    .height(180.dp),
            ) {
                AsyncImage(
                    model = R.drawable.img,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { showDetailsPlant() },
                    placeholder = painterResource(id = R.drawable.placeholder),
                    alignment = Alignment.Center
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = plantName,
                fontSize = 15.sp,
                color = Black,
                fontFamily = mulishFamily,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = plantPrice,
                fontSize = 16.sp,
                fontFamily = mulishFamily,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (showAddToCartButton) {

                Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Button(
                        onClick = { inCart = !inCart },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = White,
                            contentColor = Black
                        )
                    ) {
                        Text(
                            text = if (inCart) "Remove from Cart" else "Add to Cart",
                            fontSize = 13.sp,
                            fontFamily = mulishFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }

            }
        }
    }
}


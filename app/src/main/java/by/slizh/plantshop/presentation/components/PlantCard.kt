package by.slizh.plantshop.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import coil.compose.AsyncImage


@Composable
fun PlantCard(
    plantName: String,
    plantPrice: String
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
            AsyncImage(
                model = R.drawable.img,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp)),
                placeholder = painterResource(id = R.drawable.placeholder),
                alignment = Alignment.Center
            )

//            Image(
//                painter = painterResource(id = R.drawable.img),
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth(),
//                contentScale = ContentScale.Crop
//            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = plantName,
                fontSize = 15.sp,
                color = Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = plantPrice,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { inCart = !inCart},
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = White, contentColor = Black)
            ) {
                Text(text = if (inCart) "Remove from Cart" else "Add to Cart",
                    fontSize = 13.sp)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PlantCardPreview(

) {
    PlantCard("Peace Lily Plant", " $34.00")
}
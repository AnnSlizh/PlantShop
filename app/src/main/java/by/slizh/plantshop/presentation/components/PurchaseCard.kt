package by.slizh.plantshop.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.plantshop.domain.model.Purchase
import by.slizh.plantshop.ui.theme.Black
import by.slizh.plantshop.ui.theme.LightGreen
import by.slizh.plantshop.ui.theme.mulishFamily
import by.slizh.plantshop.util.toFormattedString

@Composable
fun PurchaseCard(purchase: Purchase) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LightGreen),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(13.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = purchase.date.toFormattedString(),
                    fontSize = 15.sp,
                    color = Black,
                    fontFamily = mulishFamily,
                    fontWeight = FontWeight.Normal
                )

                Text(
                    text = "$${purchase.totalPrice}",
                    fontSize = 15.sp,
                    color = Black,
                    fontFamily = mulishFamily,
                    fontWeight = FontWeight.Bold
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = purchase.plantNames.joinToString(", "),
                fontSize = 13.sp,
                color = Black,
                fontFamily = mulishFamily,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

package by.slizh.plantshop.presentation.components.bars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import by.slizh.plantshop.R
import by.slizh.plantshop.ui.theme.Black
import by.slizh.plantshop.ui.theme.White
import by.slizh.plantshop.ui.theme.mulishFamily

@Composable
fun CustomTopAppBar(navController: NavController) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            //  .padding(start = 20.dp, top = 16.dp, end = 20.dp)
            .background(White)
    ) {
        IconButton(modifier = Modifier
            .width(50.dp)
            .height(50.dp),
            onClick = { navController.popBackStack() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = "Return to catalog screen"
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Details",
            color = Black,
              fontFamily = mulishFamily,
              fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}
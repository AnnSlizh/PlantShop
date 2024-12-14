package by.slizh.plantshop.presentation.components.textFields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.plantshop.ui.theme.Black
import by.slizh.plantshop.ui.theme.DarkGray
import by.slizh.plantshop.ui.theme.Green
import by.slizh.plantshop.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTextField(
    fieldLabel: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
) {
    Column {
        Text(text = fieldLabel, fontSize = 17.sp, color = Black)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp),
            shape = RoundedCornerShape(20.dp),
            textStyle = TextStyle(color = Black, fontSize = 15.sp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = White,
                focusedBorderColor = Black,
                unfocusedBorderColor = Black,
                unfocusedLabelColor = DarkGray,
                focusedLabelColor = Black,
                cursorColor = Black
            ),
            isError = isError
        )
    }
}
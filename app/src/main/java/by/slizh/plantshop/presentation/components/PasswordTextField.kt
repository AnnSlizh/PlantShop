package by.slizh.plantshop.presentation.components

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.plantshop.R
import by.slizh.plantshop.ui.theme.Black
import by.slizh.plantshop.ui.theme.DarkGray
import by.slizh.plantshop.ui.theme.Gray
import by.slizh.plantshop.ui.theme.Green
import by.slizh.plantshop.ui.theme.LightGreen
import by.slizh.plantshop.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    password: String,
    passwordVisible: Boolean,
    onPasswordChange: (String) -> Unit,
    onVisibilityChange: (Boolean) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Password") },
        singleLine = true,
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Green) },
        trailingIcon = {
            IconButton(onClick = { onVisibilityChange(!passwordVisible) }) {
                Icon(
                    painter = painterResource(id = if (passwordVisible) R.drawable.eye_off_icon else R.drawable.eye_icon),
                    contentDescription = null
                )
            }
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        textStyle = TextStyle(color = Black, fontSize = 15.sp),
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = White,
            focusedBorderColor = Green,
            unfocusedBorderColor = Green,
            unfocusedLabelColor = DarkGray,
            focusedLabelColor = Green,
            cursorColor = Black
        )
    )
}
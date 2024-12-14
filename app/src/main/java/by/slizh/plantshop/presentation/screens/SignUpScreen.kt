package by.slizh.plantshop.presentation.screens

import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.slizh.plantshop.R
import by.slizh.plantshop.presentation.components.textFields.EmailTextField
import by.slizh.plantshop.presentation.components.textFields.PasswordTextField
import by.slizh.plantshop.presentation.navigation.Screen
import by.slizh.plantshop.presentation.viewModels.authorization.AuthState
import by.slizh.plantshop.presentation.viewModels.authorization.AuthViewModel
import by.slizh.plantshop.ui.theme.Black
import by.slizh.plantshop.ui.theme.Green
import by.slizh.plantshop.ui.theme.mulishFamily

@Composable
fun SignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                navController.navigate(Screen.CatalogScreen.route) {
                    popUpTo(Screen.AuthorizationScreen.route) { inclusive = true }
                }
            }

            is AuthState.Error -> {
                val errorMessage = (authState as AuthState.Error).message
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, top = 16.dp, end = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(modifier = Modifier
                .width(50.dp)
                .height(50.dp),
                onClick = { navController.navigate(Screen.AuthorizationScreen.route) }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Return to authorization screen"
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Register", fontSize = 30.sp, color = Black, fontFamily = mulishFamily,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Create your new account",
            fontSize = 16.sp,
            color = Green,
            fontFamily = mulishFamily,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(15.dp))

        EmailTextField(email) { email = it }

        Spacer(modifier = Modifier.height(20.dp))

        PasswordTextField(password, passwordVisible, { password = it }) { passwordVisible = it }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account?",
                fontSize = 16.sp,
                color = Green,
                fontFamily = mulishFamily,
                fontWeight = FontWeight.Normal
            )
            Text(
                modifier = Modifier
                    .padding(start = 3.dp)
                    .clickable { navController.navigate(Screen.SignInScreen.route) },
                text = "Sign In",
                fontSize = 16.sp,
                color = Green,
                fontFamily = mulishFamily,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(70.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { authViewModel.signUp(email = email, password = password) },
                shape = RoundedCornerShape(40),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green,
                    contentColor = Black
                )
            ) {
                Text(
                    text = "Create",
                    fontSize = 20.sp,
                    fontFamily = mulishFamily,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

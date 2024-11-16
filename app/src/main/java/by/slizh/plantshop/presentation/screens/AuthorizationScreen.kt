package by.slizh.plantshop.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.slizh.plantshop.presentation.navigation.Screen
import by.slizh.plantshop.presentation.viewModels.authorization.AuthState
import by.slizh.plantshop.presentation.viewModels.authorization.AuthViewModel
import by.slizh.plantshop.ui.theme.Black
import by.slizh.plantshop.ui.theme.DarkGray
import by.slizh.plantshop.ui.theme.Gray
import by.slizh.plantshop.ui.theme.Green
import by.slizh.plantshop.ui.theme.LightGreen
import by.slizh.plantshop.ui.theme.White
import by.slizh.plantshop.ui.theme.mulishFamily

@Composable
fun AuthorizationScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        if (authState is AuthState.Authenticated) {
            navController.navigate(Screen.CatalogScreen.route) {
                popUpTo(Screen.AuthorizationScreen.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green)
            .padding(start = 15.dp, top = 16.dp, end = 15.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Welcome to",
            fontSize = 50.sp,
            color = White,
            fontFamily = mulishFamily,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(1.dp))

        Text(
            text = "Plant Shop",
            fontSize = 50.sp,
            color = White,
            fontFamily = mulishFamily,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Browse our beautiful collection of plants and place order today",
            fontSize = 20.sp, color = Gray,
            fontFamily = mulishFamily,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(70.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate(Screen.SignInScreen.route) },
                shape = RoundedCornerShape(40),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Gray,
                    contentColor = Black
                )
            ) {
                Text(
                    text = "Sign In",
                    fontSize = 20.sp,
                    fontFamily = mulishFamily,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate(Screen.SignUpScreen.route)
                },
                shape = RoundedCornerShape(40),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Gray,
                    contentColor = Black
                )
            ) {
                Text(
                    text = "Create an account",
                    fontSize = 20.sp,
                    fontFamily = mulishFamily,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

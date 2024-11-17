package by.slizh.plantshop.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.slizh.plantshop.presentation.components.AccountCard
import by.slizh.plantshop.presentation.components.PurchaseCard
import by.slizh.plantshop.presentation.navigation.Screen
import by.slizh.plantshop.presentation.viewModels.authorization.AuthState
import by.slizh.plantshop.presentation.viewModels.authorization.AuthViewModel
import by.slizh.plantshop.presentation.viewModels.purchase.PurchaseViewModel
import by.slizh.plantshop.ui.theme.Green
import by.slizh.plantshop.ui.theme.Red
import by.slizh.plantshop.ui.theme.mulishFamily

@Composable
fun ProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
    purchaseViewModel: PurchaseViewModel = hiltViewModel()
) {
    val authState by authViewModel.authState.collectAsState()
    val purchaseState by purchaseViewModel.userPurchasesState.collectAsState()
    val userEmail = authViewModel.getCurrentUser()?.email

    LaunchedEffect(authState) {
        if (authState is AuthState.UnAuthenticated) {
            navController.navigate(Screen.AuthorizationScreen.route) {
                popUpTo(Screen.ProfileScreen.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "My Profile",
                fontSize = 18.sp,
                fontFamily = mulishFamily,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        if (userEmail != null) {
            AccountCard(userEmail) { authViewModel.signOut() }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "My purchases",
            fontSize = 20.sp,
            fontFamily = mulishFamily,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 15.dp, end = 15.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        if (purchaseState.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                color = Green
            )
        } else if (purchaseState.purchases.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp)
            ) {
                items(purchaseState.purchases) { purchase ->
                    PurchaseCard(purchase)
                }
            }
        } else if (purchaseState.error != null) {
            Text(
                text = purchaseState.error.toString(),
                color = Red,
                modifier = Modifier.padding(start = 15.dp, end = 15.dp)
            )
        } else {
            Text(
                text = "No purchases found",
                modifier = Modifier.padding(start = 15.dp, end = 15.dp)
            )
        }
    }
}



package by.slizh.plantshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.slizh.plantshop.presentation.components.bars.BottomNavigationBar
import by.slizh.plantshop.presentation.components.bars.currentRoute
import by.slizh.plantshop.presentation.navigation.Screen
import by.slizh.plantshop.presentation.screens.AuthorizationScreen
import by.slizh.plantshop.presentation.screens.CartScreen
import by.slizh.plantshop.presentation.screens.CatalogScreen
import by.slizh.plantshop.presentation.screens.DetailsScreen
import by.slizh.plantshop.presentation.screens.ProfileDetailsScreen
import by.slizh.plantshop.presentation.screens.ProfileScreen
import by.slizh.plantshop.presentation.screens.SignInScreen
import by.slizh.plantshop.presentation.screens.SignUpScreen
import by.slizh.plantshop.presentation.viewModels.authorization.AuthState
import by.slizh.plantshop.presentation.viewModels.authorization.AuthViewModel
import by.slizh.plantshop.ui.theme.Green
import by.slizh.plantshop.ui.theme.PlantShopTheme
import by.slizh.plantshop.ui.theme.White
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlantShopTheme {

                val navController = rememberNavController()

                val containerColor = when (currentRoute(navController)) {
                    Screen.AuthorizationScreen.route -> Green
                    else -> White
                }

                val authViewModel: AuthViewModel = hiltViewModel()
                val authState by authViewModel.authState.collectAsState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = containerColor,
                    bottomBar = {
                        when (currentRoute(navController)) {
                            Screen.CatalogScreen.route -> BottomNavigationBar(navController = navController)
                            Screen.CartScreen.route -> BottomNavigationBar(navController = navController)
                            Screen.ProfileScreen.route -> BottomNavigationBar(navController = navController)
                        }
                    }) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = if (authState is AuthState.Authenticated) Screen.CatalogScreen.route
                        else Screen.AuthorizationScreen.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.AuthorizationScreen.route) {
                            AuthorizationScreen(
                                navController = navController
                            )
                        }
                        composable(Screen.SignInScreen.route) { SignInScreen(navController) }
                        composable(Screen.SignUpScreen.route) { SignUpScreen(navController) }
                        composable(Screen.CatalogScreen.route) { CatalogScreen(navController) }
                        composable(Screen.CartScreen.route) { CartScreen(navController) }
                        composable(Screen.ProfileScreen.route) { ProfileScreen(navController) }
                        composable(Screen.ProfileDetailsScreen.route) { ProfileDetailsScreen(navController) }
                        composable(
                            route = Screen.DetailsScreen.route,
                            arguments = listOf(navArgument("plantId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val plantId = backStackEntry.arguments?.getInt("plantId")
                            DetailsScreen(navController, plantId = plantId)
                        }
                    }
                }
            }
        }
    }
}


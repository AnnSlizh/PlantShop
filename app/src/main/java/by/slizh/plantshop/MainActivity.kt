package by.slizh.plantshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.slizh.plantshop.presentation.components.BottomNavigationBar
import by.slizh.plantshop.presentation.components.currentRoute
import by.slizh.plantshop.presentation.navigation.Screen
import by.slizh.plantshop.presentation.screens.CartScreen
import by.slizh.plantshop.presentation.screens.CatalogScreen
import by.slizh.plantshop.presentation.screens.DetailsScreen
import by.slizh.plantshop.presentation.screens.ProfileScreen
import by.slizh.plantshop.ui.theme.PlantShopTheme
import by.slizh.plantshop.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlantShopTheme {

                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = White, bottomBar = {
                    if (currentRoute(navController) != Screen.DetailsScreen.route) {
                        BottomNavigationBar(navController = navController)
                    }
                }) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = Screen.CatalogScreen.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.CatalogScreen.route) { CatalogScreen(navController) }
                        composable(Screen.CartScreen.route) { CartScreen(navController) }
                        composable(Screen.ProfileScreen.route) { ProfileScreen(navController) }
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


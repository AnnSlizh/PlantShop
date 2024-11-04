package by.slizh.plantshop.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import by.slizh.plantshop.R
import by.slizh.plantshop.presentation.navigation.Screen
import by.slizh.plantshop.ui.theme.Black
import by.slizh.plantshop.ui.theme.DarkGray
import by.slizh.plantshop.ui.theme.Green
import by.slizh.plantshop.ui.theme.Red
import by.slizh.plantshop.ui.theme.White

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(Screen.CatalogScreen, Screen.CartScreen, Screen.ProfileScreen)
    val currentRoute = currentRoute(navController)

    NavigationBar(
        containerColor = White,
        contentColor = Black
    ) {
        items.forEach { screen ->
            val isSelected = currentRoute == screen.route
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = when (screen) {
                                Screen.CatalogScreen -> R.drawable.book_icon
                                Screen.CartScreen -> R.drawable.cart_icon
                                Screen.ProfileScreen -> R.drawable.account_icon
                                else -> throw IllegalStateException("Screen not supported in BottomNavigationBar")
                            }
                        ),
                        contentDescription = screen.route
                    )
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Green,
                    unselectedIconColor = DarkGray,
                    indicatorColor = White,
                    disabledIconColor = White
                ),
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
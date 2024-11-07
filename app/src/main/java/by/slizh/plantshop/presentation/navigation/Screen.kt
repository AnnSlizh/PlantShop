package by.slizh.plantshop.presentation.navigation

sealed class Screen(val route: String) {
    object AuthorizationScreen : Screen(route = "authorization_screen")
    object LogInScreen : Screen(route = "login_screen")
    object RegistrationScreen : Screen(route = "registration_screen")
    object CatalogScreen : Screen(route = "catalog_screen")
    object CartScreen : Screen(route = "cart_screen")
    object ProfileScreen : Screen(route = "profile_screen")
    object DetailsScreen : Screen(route = "details_screen/{plantId}") {
        fun createRoute(plantId: Int) = "details_screen/$plantId"
    }
}
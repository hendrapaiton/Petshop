package cloud.hendra.petshop.ui.navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cloud.hendra.petshop.ui.screen.MainScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen()
        }
        composable("main") {
            MainScreen()
        }
    }
}

sealed class Routes(val route: String) {
    object Main : Routes("main")
    object Login : Routes("login")
}
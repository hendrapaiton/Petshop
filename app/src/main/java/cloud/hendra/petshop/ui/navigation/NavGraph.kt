package cloud.hendra.petshop.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cloud.hendra.petshop.ui.screen.GuardScreen
import cloud.hendra.petshop.ui.screen.LoginScreen
import cloud.hendra.petshop.ui.screen.MainScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "guard") {
        composable("guard") {
            GuardScreen(
                onAuthenticated = { navController.navigate("main") },
                onUnauthenticated = { navController.navigate("login") }
            )
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("main") {
            MainScreen()
        }
    }
}

sealed class Routes(val route: String) {
    object Guard : Routes("guard")
    object Main : Routes("main")
    object Login : Routes("login")
}
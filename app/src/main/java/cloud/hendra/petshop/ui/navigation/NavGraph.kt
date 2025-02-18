package cloud.hendra.petshop.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cloud.hendra.petshop.ui.screen.GuardScreen
import cloud.hendra.petshop.ui.screen.LoginPage
import cloud.hendra.petshop.ui.screen.ReportPage
import cloud.hendra.petshop.ui.screen.SaldoPage
import cloud.hendra.petshop.ui.screen.SalesPage
import cloud.hendra.petshop.ui.screen.StockPage

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "guard") {
        composable("guard") {
            GuardScreen(
                onAuthenticated = { navController.navigate("saldo") },
                onUnauthenticated = { navController.navigate("login") }
            )
        }
        composable("login") {
            LoginPage(navController)
        }
        composable("saldo") {
            SaldoPage(navController)
        }
        composable("sales"){
            SalesPage(navController)
        }
        composable("stock"){
            StockPage(navController)
        }
        composable("report"){
            ReportPage(navController)
        }
    }
}

sealed class Routes(val route: String) {
    object Guard : Routes("guard")
    object Login : Routes("login")
    object Saldo : Routes("saldo")
    object Sales : Routes("sales")
    object Stock : Routes("stock")
    object Report : Routes("report")
}
import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import cloud.hendra.petshop.ui.component.component.ErrorScreen
import cloud.hendra.petshop.ui.component.component.LoadingScreen
import cloud.hendra.petshop.ui.component.login.LoginTitle
import cloud.hendra.petshop.ui.viewmodel.IndexViewModel
import cloud.hendra.petshop.utils.Result
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(navController: NavHostController, viewModel: IndexViewModel = koinViewModel()) {
    val uiState by viewModel.uiState
    Log.d("LoginTitle", "state: $uiState")

    when (val state = uiState) {
        is Result.Loading -> LoadingScreen()
        is Result.Success -> LoginTitle(navController, state.data)
        is Result.Error -> ErrorScreen(state.message.toString())
    }
}

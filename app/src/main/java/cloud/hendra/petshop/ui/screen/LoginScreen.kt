import androidx.compose.runtime.*
import cloud.hendra.petshop.ui.component.component.ErrorScreen
import cloud.hendra.petshop.ui.component.component.LoadingScreen
import cloud.hendra.petshop.ui.component.login.LoginTitle
import cloud.hendra.petshop.ui.viewmodel.IndexViewModel
import cloud.hendra.petshop.utils.Result
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(viewModel: IndexViewModel = koinViewModel()) {
    val uiState by viewModel.uiState

    when (val state = uiState) {
        is Result.Loading -> LoadingScreen()
        is Result.Success -> LoginTitle(state.index)
        is Result.Error -> ErrorScreen(state.message)
    }
}

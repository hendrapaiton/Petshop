package cloud.hendra.petshop.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cloud.hendra.petshop.ui.viewmodel.ProtectedViewModel
import cloud.hendra.petshop.utils.state.ProtectedState
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: ProtectedViewModel = koinViewModel()) {
    val uiState by viewModel.uiState

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (val state = uiState) {
            is ProtectedState.Idle -> Unit
            is ProtectedState.Loading -> LoadingView()
            is ProtectedState.Success -> DataView(state.data.detail)
            is ProtectedState.Error -> ErrorView(state.message)
            is ProtectedState.Unauthorized -> ErrorView("Unauthorized")
        }
    }
}

@Composable
fun ErrorView(message: String) {
    Text(text = message)
}

@Composable
fun DataView(detail: String) {
    Text(text = detail)
}

@Composable
fun LoadingView() {
    CircularProgressIndicator()
}
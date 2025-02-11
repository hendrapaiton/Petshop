package cloud.hendra.petshop.ui.screen

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import cloud.hendra.petshop.domain.model.Index
import cloud.hendra.petshop.ui.viewmodel.IndexViewModel
import cloud.hendra.petshop.utils.Result
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: IndexViewModel = koinViewModel()) {
    val uiState by viewModel.uiState
    when (val state = uiState) {
        is Result.Loading -> LoadingView()
        is Result.Success -> DataView(state.index)
        is Result.Error -> ErrorView(state.message)
    }
}

@Composable
fun ErrorView(message: String) {
    Text(text = message)
}

@Composable
fun DataView(index: Index) {
    Text(text = index.name)
}

@Composable
fun LoadingView() {
    CircularProgressIndicator()
}
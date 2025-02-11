package cloud.hendra.petshop.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cloud.hendra.petshop.data.remote.dto.IndexDto
import cloud.hendra.petshop.ui.viewmodel.IndexViewModel
import cloud.hendra.petshop.utils.Result
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(viewModel: IndexViewModel = koinViewModel()) {
    val uiState by viewModel.uiState

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (val state = uiState) {
            is Result.Loading -> LoadingView()
            is Result.Success -> DataView(state.index)
            is Result.Error -> ErrorView(state.message)
        }
    }
}

@Composable
fun ErrorView(message: String) {
    Text(text = message)
}

@Composable
fun DataView(index: IndexDto) {
    Text(text = index.name)
}

@Composable
fun LoadingView() {
    CircularProgressIndicator()
}
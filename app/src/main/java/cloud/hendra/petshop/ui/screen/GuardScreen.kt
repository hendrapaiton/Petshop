package cloud.hendra.petshop.ui.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import cloud.hendra.petshop.ui.viewmodel.GuardViewModel
import cloud.hendra.petshop.utils.state.GuardState
import org.koin.androidx.compose.koinViewModel

@Composable
fun GuardScreen(
    viewModel: GuardViewModel = koinViewModel(),
    onAuthenticated: () -> Unit,
    onUnauthenticated: () -> Unit,
) {
    val guardState = viewModel.authState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.checkAuthState()
    }

    LaunchedEffect(guardState) {
        Log.d("GuardScreen", "guardState: $guardState")
        when (guardState) {
            is GuardState.NeedsRefresh -> Unit
            is GuardState.Authenticated -> onAuthenticated()
            is GuardState.Unauthenticated -> onUnauthenticated()
            else -> Unit
        }
    }
}
package cloud.hendra.petshop.ui.component.component

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ErrorScreen(message: String) {
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_LONG).show()
}
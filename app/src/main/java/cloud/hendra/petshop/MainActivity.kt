package cloud.hendra.petshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import cloud.hendra.petshop.ui.navigation.NavGraph
import cloud.hendra.petshop.ui.theme.PetshopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetshopTheme {
                AndroidContextWrapper {
                    NavGraph()
                }
            }
        }
    }
}

@Composable
fun AndroidContextWrapper(content: @Composable () -> Unit) {
    val context = LocalContext.current
    CompositionLocalProvider(
        LocalContext provides context
    ) {
        content()
    }
}
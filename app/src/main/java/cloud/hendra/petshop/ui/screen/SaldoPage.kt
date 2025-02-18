package cloud.hendra.petshop.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cloud.hendra.petshop.ui.viewmodel.ProtectedViewModel
import cloud.hendra.petshop.ui.viewmodel.RefreshViewModel
import cloud.hendra.petshop.utils.state.ProtectedState
import org.koin.androidx.compose.koinViewModel

@Composable
fun SaldoPage(navController: NavController, viewModel: ProtectedViewModel = koinViewModel()) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Ternak Cuan", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        Text(
            text = "Shift".uppercase(),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 3.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            val selectedOption = remember { mutableStateOf("Pagi") }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOption.value == "Pagi",
                    onClick = { selectedOption.value = "Pagi" },
                    colors = RadioButtonDefaults.colors(selectedColor = Color.Blue)
                )
                Text(text = "Pagi")
            }
            Spacer(modifier = Modifier.width(32.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOption.value == "Sore",
                    onClick = { selectedOption.value = "Sore" },
                    colors = RadioButtonDefaults.colors(selectedColor = Color.Blue)
                )
                Text(text = "Sore")
            }
        }

        var text = remember { mutableStateOf("") }
        var isFocused = remember { mutableStateOf(false) }

        BasicTextField(
            value = text.value,
            onValueChange = { text.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            decorationBox = { innerTextField ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = if (isFocused.value) Color.Blue else Color.Gray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                            .background(
                                color = if (isFocused.value) Color.LightGray.copy(alpha = 0.1f) else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        if (text.value.isEmpty()) {
                            Text(
                                text = "Saldo awal...",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        innerTextField()
                    }
                }
            },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp
            )
        )
        Button(
            onClick = { navController.navigate("sales") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Buka Toko")
        }
    }

//    val uiState by viewModel.uiState
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        when (val state = uiState) {
//            is ProtectedState.Idle -> Unit
//            is ProtectedState.Loading -> LoadingView()
//            is ProtectedState.Success -> DataView(state.data.detail)
//            is ProtectedState.Error -> ErrorView(state.message)
//            is ProtectedState.Unauthorized -> ErrorView("Unauthorized")
//        }
//    }
//}
//
//@Composable
//fun ErrorView(message: String) {
//    Text(text = message)
//}
//
//@Composable
//fun DataView(detail: String, viewModel: RefreshViewModel = koinViewModel()) {
//    val uiState by viewModel.uiState
//
//    Text(text = detail)
//    Button(onClick = {
//        viewModel.getAccessToken()
//    }) {
//        when (val state = uiState) {
//            is cloud.hendra.petshop.utils.state.Result.Loading -> CircularProgressIndicator()
//            is cloud.hendra.petshop.utils.state.Result.Success -> Text(text = state.data.token)
//            is cloud.hendra.petshop.utils.state.Result.Error -> Text(text = state.message.toString())
//        }
//        Text(text = "Refresh Token")
//    }
//}
//
//@Composable
//fun LoadingView() {
//    CircularProgressIndicator()
}
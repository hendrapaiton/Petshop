package cloud.hendra.petshop.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.collectAsState
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
import cloud.hendra.petshop.ui.viewmodel.SaldoViewModel
import cloud.hendra.petshop.utils.state.Result.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun SaldoPage(navController: NavController, viewModel: SaldoViewModel = koinViewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    val shift = remember { mutableStateOf("P") }
    val nominal = remember { mutableStateOf("") }
    val isFocused = remember { mutableStateOf(false) }

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
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = shift.value == "P",
                    onClick = { shift.value = "P" },
                    colors = RadioButtonDefaults.colors(selectedColor = Color.Blue)
                )
                Text(text = "Pagi")
            }
            Spacer(modifier = Modifier.width(32.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = shift.value == "S",
                    onClick = { shift.value = "S" },
                    colors = RadioButtonDefaults.colors(selectedColor = Color.Blue)
                )
                Text(text = "Sore")
            }
        }

        BasicTextField(
            value = nominal.value,
            onValueChange = { nominal.value = it },
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
                        if (nominal.value.isEmpty()) {
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
            onClick = {
                viewModel.openStore(shift = shift.value, awal = nominal.value.toInt())
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Buka Toko")
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (val state = uiState.value) {
            is Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.Blue,
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Memproses...",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            is Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color(0xFFFFEBEE), RoundedCornerShape(8.dp))
                        .border(1.dp, Color(0xFFFFCDD2), RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = state.message ?: "Terjadi kesalahan",
                            color = Color.Red,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            is Success -> {
                navController.navigate("sales")
            }

            else -> Unit
        }
    }
}
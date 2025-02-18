package cloud.hendra.petshop.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockPage(navController: NavHostController) {
    var searchText by remember { mutableStateOf(TextFieldValue("Cari Barang...")) }
    var selectedOption by remember { mutableStateOf("Rak") }
    var inputText by remember { mutableStateOf(TextFieldValue("0")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Stock", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold) },
                colors = TopAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.White,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.White,
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .fillMaxSize(),
        ) {
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(4.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Nama Barang",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedOption == "Rak",
                            onClick = { selectedOption = "Rak" }
                        )
                        Text("Rak")
                        Spacer(modifier = Modifier.width(8.dp))
                        RadioButton(
                            selected = selectedOption == "Masuk",
                            onClick = { selectedOption = "Masuk" }
                        )
                        Text("Masuk")
                        Spacer(modifier = Modifier.width(8.dp))
                        RadioButton(
                            selected = selectedOption == "Keluar",
                            onClick = { selectedOption = "Keluar" }
                        )
                        Text("Keluar")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        BasicTextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            modifier = Modifier
                                .weight(1f)
                                .background(Color.White, shape = RoundedCornerShape(4.dp))
                                .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                                .padding(8.dp),
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Right)
                        )

                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { }) {
                            Text("Ok")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Nama Barang", fontWeight = FontWeight.Bold)
                    Text("Harga", fontWeight = FontWeight.Bold)
                    Text("Qty", fontWeight = FontWeight.Bold)
                }
                listOf(
                    Triple("Barang A", 10000, 10),
                    Triple("Barang B", 15000, 5),
                    Triple("Barang C", 20000, 8)
                ).forEach { (name, price, qty) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(name)
                        Text(price.toString())
                        Text(qty.toString())
                    }
                    HorizontalDivider(color = Color.Gray, thickness = 0.5.dp)
                }
            }
        }
    }
}
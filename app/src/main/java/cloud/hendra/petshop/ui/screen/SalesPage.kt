package cloud.hendra.petshop.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesPage(navController: NavController) {
    var searchText = remember { mutableStateOf(TextFieldValue("Cari Barang...")) }
    var initialText = remember { mutableStateOf(TextFieldValue("CD")) }
    var quantityText = remember { mutableStateOf(TextFieldValue("0")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sales", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold) },
                colors = TopAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = Color.White,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.White,
                ),
                actions = {
                    IconButton(onClick = { navController.navigate("stock") }) {
                        Text(
                            text = "Stock",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    }
                }
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
                value = searchText.value,
                onValueChange = { searchText.value = it },
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
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(0.2f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(70.dp)
                                .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                        ) {
                            Text(
                                text = initialText.value.text.takeIf { it.isNotEmpty() }
                                    ?.uppercase() ?: "",
                                fontSize = 20.sp,
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.8f)
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "Nama Barang".uppercase(),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Rp. 18.000", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.width(16.dp))
                            IconButton(onClick = {}) {
                                Icon(imageVector = Icons.Default.Clear, contentDescription = "")
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            BasicTextField(
                                value = quantityText.value,
                                onValueChange = { quantityText.value = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White, shape = RoundedCornerShape(4.dp))
                                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Button(onClick = { /* Handle Cash */ }) {
                            Text("Cash")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = { /* Handle Qris */ }) {
                            Text("Qris")
                        }
                    }
                    Column {
                        Text(
                            text = "Total",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraLight
                        )
                        Text(
                            text = "Rp. 200.000",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
    }
}
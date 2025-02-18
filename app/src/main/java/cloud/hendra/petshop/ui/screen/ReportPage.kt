package cloud.hendra.petshop.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ReportPage(navController: NavHostController) {
    Scaffold(
        topBar = {
            Text(
                text = "Report",
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(16.dp)
            )
        }
    ) { paddingValues ->
        Column {
            Card(
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(paddingValues)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    InvoiceRow(info = "Saldo Awal", nominal = 0)
                    InvoiceRow(info = "Omset QRIS", nominal = 0)
                    InvoiceRow(info = "Omset Cash", nominal = 0)
                    InvoiceRow(info = "Total Omset", nominal = 0)
                    InvoiceRow(info = "Saldo Akhir", nominal = 0)
                }
            }
            val noteText = remember { mutableStateOf("") }

            Card(
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Konfirmasi Saldo",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    val confirmationText =
                        remember { mutableStateOf("Saldo Anda telah dikonfirmasi.") }
                    BasicTextField(
                        value = confirmationText.value,
                        onValueChange = { confirmationText.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(4.dp))
                            .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                            .padding(8.dp),
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Text(
                        text = "Catatan:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    BasicTextField(
                        value = noteText.value,
                        onValueChange = { noteText.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(Color.White, shape = RoundedCornerShape(4.dp))
                            .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { /* Handle save action */ },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Simpan")
                    }
                }
            }
        }
    }
}

@Composable
fun InvoiceRow(info: String, nominal: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = info, fontWeight = FontWeight.Bold)
        Text(text = nominal.toString(), fontWeight = FontWeight.Normal)
    }
}
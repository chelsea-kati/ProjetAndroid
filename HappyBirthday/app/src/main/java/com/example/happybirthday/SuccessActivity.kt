package com.example.happybirthday

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class SuccessActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val amount = intent.getStringExtra("AMOUNT") ?: "0"
        val phone = intent.getStringExtra("PHONE") ?: "N/A"

        setContent {
            HappyBirthdayTheme {
                SuccessScreen(
                    amount = amount,
                    phone = phone,
                    onGoHome = {
                        val intent = Intent(this, BalanceActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    },
                    onNewTransaction = { finish() }
                )
            }
        }
    }
}

@Composable
fun SuccessScreen(amount: String, phone: String, onGoHome: () -> Unit, onNewTransaction: () -> Unit) {
    Scaffold(containerColor = Color(0xFFE9F1F8)) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // --- 1. CERCLE VERT AVEC CHECK ---
            Surface(
                modifier = Modifier.size(120.dp),
                shape = CircleShape,
                color = Color(0xFF4ADE80) // Vert éclatant
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Default.Check,
                        null,
                        tint = Color.White,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- 2. TITRES ---
            Text("Cash-in Successful", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Text("$amount BIF", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D47A1))

            Spacer(modifier = Modifier.height(24.dp))

            // --- 3. MESSAGE DÉTAILLÉ ---
            Text(
                text = "Your cash-in of $amount BIF to $phone was successfully done.\nTransaction ID: #69eb1bcfc327d8e80b15e366",
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            // --- 4. BOUTONS ---
            Button(
                onClick = onGoHome,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1))
            ) {
                Text("Go To Home Screen", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onNewTransaction) {
                Text("Make New Transaction", color = Color(0xFF0D47A1), fontWeight = FontWeight.Bold)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SuccessActivityPreview() {
    HappyBirthdayTheme {
        SuccessScreen(
            amount = "1,000",
            phone = "09506569666",
            onGoHome = {},
            onNewTransaction = {}
        )
    }
}
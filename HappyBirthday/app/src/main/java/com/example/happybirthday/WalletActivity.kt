package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class WalletActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                WalletScreen()
            }
        }
    }
}

@Composable
fun WalletScreen() {
    var selectedTab by remember { mutableStateOf("Wallet")}
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF2F4F8), // Fond gris clair
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp)
        ) {
            // --- TOP BAR ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "My Wallet",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // --- 1. APPEL DES COMPOSANTS ---
            WalletBalanceCard()
            Spacer(modifier = Modifier.height(20.dp))
            StatsCard()
            Spacer(modifier = Modifier.height(20.dp))
            ActivitiesCard()

            Spacer(modifier = Modifier.height(100.dp)) // Pour voir le scroll
        }
    }
}

// --- LES COMPOSANTS DOIVENT ÊTRE SORTIS DE WALLETSCREEN ---

@Composable
fun WalletBalanceCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Solde", color = Color.Gray, fontSize = 14.sp)
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "141,000",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A1A)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("BIF", fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 6.dp))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "MOVE MONEY",
                color = Color(0xFF0D47A1),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun StatsCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Stats", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Surface(color = Color(0xFFF5F7FA), shape = RoundedCornerShape(8.dp)) {
                    Text("4 semaines ⌄", modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp), fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            StatIndicator(Icons.Default.ArrowUpward, Color(0xFF4ADE80), "0 BIF")
            Spacer(modifier = Modifier.height(8.dp))
            StatIndicator(Icons.Default.ArrowDownward, Color(0xFFF87171), "2,000 BIF")

            Spacer(modifier = Modifier.height(24.dp))

            // SIMULATION DE GRAPHIQUE
            Box(modifier = Modifier.fillMaxWidth().height(150.dp)) {
                Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
                    repeat(4) { HorizontalDivider(thickness = 0.5.dp, color = Color(0xFFEEEEEE)) }
                }
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .fillMaxHeight(0.7f)
                        .background(Color(0xFF4ADE80), RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                        .align(Alignment.BottomCenter)
                )
            }
            Text("Apr 22", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun StatIndicator(icon: ImageVector, color: Color, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(shape = CircleShape, color = color.copy(alpha = 0.1f), modifier = Modifier.size(24.dp)) {
            Icon(icon, null, tint = color, modifier = Modifier.size(14.dp))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(text, fontWeight = FontWeight.Bold, fontSize = 15.sp)
    }
}

@Composable
fun ActivitiesCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("Activités", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(
                "Remarque : les gains sont inclus dans ce montant.",
                fontSize = 12.sp,
                color = Color.Gray,
                style = androidx.compose.ui.text.TextStyle(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Surface(color = Color(0xFFF0F5FD), shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.ArrowUpward, null, tint = Color(0xFF4ADE80), modifier = Modifier
                        .background(Color.White, CircleShape)
                        .padding(4.dp)
                        .size(16.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("3,000", fontWeight = FontWeight.Bold)
                    Text(" BIF", color = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            TransactionItemWallet("Agent - Wallet cashin", "15/04/2026 10:29", "-1,000 BIF")
            TransactionItemWallet("Agent - Wallet cashin", "22/04/2026 09:24", "-1,000 BIF")
            TransactionItemWallet("Agent - Wallet cashin", "22/04/2026 09:36", "-1,000 BIF")
        }
    }
}

@Composable
fun TransactionItemWallet(title: String, date: String, amount: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(shape = CircleShape, color = Color(0xFFFFEBEE), modifier = Modifier.size(40.dp)) {
                Icon(Icons.Default.ArrowDownward, null, tint = Color.Red, modifier = Modifier.padding(8.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(title, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Text(date, fontSize = 12.sp, color = Color.Gray)
            }
        }
        Text(amount, color = Color.Red, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun WalletPreview() {
    HappyBirthdayTheme {
        WalletScreen()
    }
}
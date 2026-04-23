package com.example.happybirthday

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

@Composable
fun HistoryScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFE9F1F8), // Fond bleu clair
    ) { paddingValues ->
        // Le scroll est défini ici sur la Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()) // C'est cette ligne qui permet de scroller
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // --- 1. TOP BAR ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "History",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Surface(
                    modifier = Modifier
                        .size(45.dp)
                        .clickable { /* Filtre */ },
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Sort, "Filter", modifier = Modifier.size(24.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // --- 2. BARRE DE SELECTION DE DATE ---
            Surface(
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color.White
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.DateRange, null, tint = Color.DarkGray, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("From Apr 22, 2016 to Apr 22, 2028", color = Color.Gray, fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // --- 3. SECTION "TODAY" ---
            SectionDateTitle("Today")
            TransactionCard("Agent - Wallet cashin", "NDIKUMANA YOHANA", "1,000", "09:36", "done")
            TransactionCard("Agent - Wallet cashin", "NDIKUMANA YOHANA", "1,000", "09:24", "done")

            Spacer(modifier = Modifier.height(20.dp))

            // --- 4. SECTION "WEDNESDAY" ---
            SectionDateTitle("Wednesday, Apr 15")
            TransactionCard("Agent - Wallet cashin", "DON KAVUMU", "1,000", "10:29", "done")

            Spacer(modifier = Modifier.height(20.dp))

            // --- 5. SECTION "MONDAY" ---
            SectionDateTitle("Monday, Sep 2")
            TransactionCard("Agent - Wallet cashin", "HUZAIFA DRIVER", "5,000", "17:34", "done")

            Spacer(modifier = Modifier.height(20.dp))

            // --- AJOUT DE PLUS DE CARTES POUR TESTER LE SCROLL ---
            SectionDateTitle("Thursday, Aug 29")
            TransactionCard("Agent - Wallet cashin", "JEAN CLAUDE", "2,500", "08:15", "done")
            TransactionCard("Agent - Wallet cashin", "MARIE ROSE", "12,000", "14:40", "done")

            Spacer(modifier = Modifier.height(20.dp))

            SectionDateTitle("Last Week")
            TransactionCard("Agent - Wallet cashin", "SABINE W.", "3,000", "11:20", "done")
            TransactionCard("Agent - Wallet cashin", "PAUL B.", "7,500", "16:05", "done")
            TransactionCard("Agent - Wallet cashin", "KEVIN G.", "1,200", "19:50", "done")

            // Un grand spacer à la fin pour ne pas être caché par la barre de navigation
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun SectionDateTitle(text: String) {
    Text(
        text = text,
        color = Color.Gray,
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun TransactionCard(title: String, subtitle: String, amount: String, time: String, status: String) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier.padding(20.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF1A1A1A))
                Text(text = subtitle, fontSize = 13.sp, color = Color.Gray)
                Text(text = "Abc", fontSize = 12.sp, color = Color.LightGray)
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Schedule, null, tint = Color.LightGray, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = time, fontSize = 12.sp, color = Color.LightGray)
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(text = amount, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "BIF", fontSize = 12.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Surface(color = Color(0xFFD1F2EB), shape = RoundedCornerShape(12.dp)) {
                    Text(
                        text = status,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        color = Color(0xFF1ABC9C),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HappyBirthdayTheme {
        HistoryScreen()
    }
}
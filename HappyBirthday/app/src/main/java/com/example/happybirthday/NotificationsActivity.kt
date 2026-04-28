package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Notifications
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

class NotificationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                NotificationsScreen(onBack = { finish() })
            }
        }
    }
}

@Composable
fun NotificationsScreen(onBack: () -> Unit) {
    // 1. Simulation des données (C'est ici que vous mettrez le endpoint plus tard)
    val notifications = listOf(
        NotificationItem(1, "Send Money", "Send Money 1000 BIF from 0961618465 to 0979668319 at 09:33 17/07/2024.", "17/07/2024", "09:33"),
        NotificationItem(2, "Send Money", "Send Money 1000 BIF from 0961618465 to 0979668319 at 09:12 17/07/2024.", "17/07/2024", "09:12"),
        NotificationItem(3, "Send Money", "Send Money 1000 BIF from 0961618465 to 0979668319 at 09:04 17/07/2024.", "17/07/2024", "09:04"),
        NotificationItem(4, "Send Money", "Send Money 1000 BIF from 0961618465 to 0979668319 at 08:58 17/07/2024.", "17/07/2024", "08:58"),
        NotificationItem(5, "Cash In by Operator", "Cash In 50000 BIF from System at 08:41 13/06/2023.", "13/06/2023", "08:41"),
        NotificationItem(6, "Cash In by Operator", "Cash In 100000 BIF from System at 08:38 13/06/2023.", "13/06/2023", "08:38")
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFE9F1F8) // Fond bleu très clair
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 18.dp)
        ) {
            // --- TOP BAR ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                // Bouton retour à gauche
                Surface(
                    modifier = Modifier
                        .size(45.dp)
                        .align(Alignment.CenterStart)
                        .clickable { onBack() },
                    shape = CircleShape,
                    color = Color.White
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.ArrowBackIosNew, "Retour", modifier = Modifier.size(18.dp))
                    }
                }

                // Titre centré
                Text(
                    text = "Notifications",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            // --- LISTE DES NOTIFICATIONS ---
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()), // Permet de scroller
                verticalArrangement = Arrangement.spacedBy(14.dp) // Espace entre les cartes
            ) {
                notifications.forEach { notification ->
                    NotificationCard(notification)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun NotificationCard(notification: NotificationItem) {
    // La couleur des cartes sur l'image est un bleu un peu plus foncé que le fond
    val cardBlue = Color(0xFFB9D4F1)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = cardBlue
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icône de cloche dans un carré bleu
            Surface(
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFF2490FF) // Bleu vif
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Textes
            Column {
                Text(
                    text = notification.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF151515)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = notification.message,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = Color(0xFF333333)
                )
            }
        }
    }
}
// --- AJOUT DE LA DATA CLASS ICI ---
data class NotificationItem(
    val id: Int,
    val title: String,
    val message: String,
    val date: String,
    val time: String
)

@Preview(showBackground = true)
@Composable
fun NotificationsPreview() {
    HappyBirthdayTheme {
        NotificationsScreen(onBack = {})
    }
}

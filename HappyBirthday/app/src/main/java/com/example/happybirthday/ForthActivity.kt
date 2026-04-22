package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme


class ForthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ForthScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)//Cette ligne sert à débloquer l'accès aux composants les plus récents
@Composable
fun ForthScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "CAFE GOURMAND") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: profil action */ }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profil"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: qr code action */ }) {
                        Icon(
                            imageVector = Icons.Default.QrCodeScanner,
                            contentDescription = "QR Code"
                        )
                    }
                    IconButton(onClick = { /* TODO: notifications action */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Blue, // Bleu standard
                    titleContentColor = Color.Black, // Pour que le texte soit lisible
                    actionIconContentColor = Color.Black, // Pour que les icônes soient lisibles
                    navigationIconContentColor = Color.Black
                )
                )

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            ShopInformationSection()
        }
    }
}

@Composable
fun ShopInformationSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        // Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Information de la boutique",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            TextButton(onClick = { /* TODO: Modifier action */ }) {
                Text(
                    text = "Modifier",
                    color = Color.Blue,
                    fontSize = 14.sp
                )
            }
        }

        // Shop Details
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Shop Image
            Image(
                painter = painterResource(id = R.drawable.cafegourmand),
                contentDescription = "Photo du café",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Shop Text Details
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "CAFÉ GOURMAND",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Restaurants & Dining/Fast Food",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "BUJUMBURA-BURUNDI",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .padding(end = 2.dp),
                            tint = Color(0xFFFFC107)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "0(0)",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                TextButton(onClick = { /* TODO: Show reviews */ }) {
                    Text(
                        text = "See more reviews",
                        color = Color.Blue,
                        fontSize = 14.sp
                    )
                }
            }
        }

        }


        Spacer(modifier = Modifier.height(24.dp))

        ShopMenuItem(
            icon = Icons.Default.ShoppingCart,
            title = "Gestion de produits",
            subtitle = "Gérer les produits",
            onClick = { /* TODO: Navigate to product management */ }
        )
        Divider(color = Color.LightGray)

        ShopMenuItem(
            icon = Icons.Default.Star,
            title = "Notes",
            subtitle = "Consulter les notes et les commentaires",
            onClick = { /* TODO: Navigate to reviews */ }
        )
        Divider(color = Color.LightGray)

        ShopMenuItem(
            icon = Icons.Default.ReceiptLong,
            title = "Commandes",
            subtitle = "Consulter les commandes",
            onClick = { /* TODO: Navigate to orders */ }
        )
        Divider(color = Color.LightGray)

        ShopMenuItem(
            icon = Icons.Default.LocalShipping,
            title = "Livraison",
            subtitle = "Consulter les livraisons",
            onClick = { /* TODO: Navigate to deliveries */ }
        )
        Divider(color = Color.LightGray)

        ShopMenuItem(
            icon = Icons.Default.Group,
            title = "Gestion du staff",
            subtitle = "Ajouter/Modifier/Supprimer un utilisateur",
            onClick = { /* TODO: Navigate to staff management */ }
        )
        Divider(color = Color.LightGray)

        ShopMenuItem(
            icon = Icons.Default.CalendarMonth,
            title = "Présence",
            subtitle = "Gérer votre horaire",
            onClick = { /* TODO: Navigate to attendance */ }
        )
    }


@Composable
fun ShopMenuItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(42.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "Suivant",
            tint = Color.Gray
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ForthScreenPreview() {
    HappyBirthdayTheme {
        ForthScreen()
    }
}
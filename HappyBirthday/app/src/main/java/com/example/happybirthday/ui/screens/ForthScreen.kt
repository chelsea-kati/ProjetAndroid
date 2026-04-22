package com.example.happybirthday.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.happybirthday.R
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

@Composable
fun ForthScreen() {
    Scaffold(
        topBar = {
            ReferenceTopBar()
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
fun ReferenceTopBar() {
    val topBarBlue = Color(0xFF0E5BA9)
    val actionCircle = Color.White.copy(alpha = 0.10f)
    val chipColor = Color.White.copy(alpha = 0.12f)

    Surface(
        color = topBarBlue,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                TopBarCircleAction(
                    onClick = { },
                    containerColor = actionCircle
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profil",
                        tint = Color.White,
                        modifier = Modifier.size(26.dp)
                    )
                }

                LocationChip(
                    city = "Bujumbura",
                    containerColor = chipColor
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TopBarCircleAction(
                    onClick = { },
                    containerColor = actionCircle
                ) {
                    Icon(
                        imageVector = Icons.Default.QrCodeScanner,
                        contentDescription = "Scanner QR",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Box {
                    TopBarCircleAction(
                        onClick = { },
                        containerColor = actionCircle
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = (-2).dp, y = 2.dp)
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFF4B4B))
                    )
                }
            }
        }
    }
}

@Composable
fun TopBarCircleAction(
    onClick: () -> Unit,
    containerColor: Color,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .size(58.dp)
            .clip(CircleShape)
            .background(containerColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun LocationChip(
    city: String,
    containerColor: Color
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(22.dp))
            .background(containerColor)
            .clickable(onClick = { })
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(22.dp),
            shape = RoundedCornerShape(6.dp),
            color = Color.Transparent,
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "B",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = city,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "Choisir la ville",
            tint = Color.White
        )
    }
}

@Composable
fun ShopInformationSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Shop Image
            Image(
                painter = painterResource(id = R.drawable.cafegourmand),
                contentDescription = "Photo du café",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            // Shop Text Details
            Column {
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
                // Rating
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .width(16.dp)
                                .height(16.dp),
                            tint = Color(0xFFFFC107)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "0(0)",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    // See more reviews
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
            .padding(vertical = 12.dp),
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

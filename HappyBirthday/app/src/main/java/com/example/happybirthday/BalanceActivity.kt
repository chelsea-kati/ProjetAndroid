package com.example.happybirthday

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

@Composable
fun BalanceScreen() {
    // États principaux pour l'écran
    var isBalanceVisible by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableStateOf("Home") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
                .background(Color(0xFFF2F4F8))
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            BalanceTopSection(
                isBalanceVisible = isBalanceVisible,
                onToggleVisibility = { isBalanceVisible = !isBalanceVisible }
            )
            OperationsBody()
        }
    }
}

@Composable
fun BalanceTopSection(isBalanceVisible: Boolean, onToggleVisibility: () -> Unit) {
    val topBarBlue = Color(0xFF0E5BA9)
    val actionCircle = Color.White.copy(alpha = 0.10f)
    val chipColor = Color.White.copy(alpha = 0.12f)

    var selectedCity by remember { mutableStateOf("Bujumbura") }

    Surface(
        color = topBarBlue,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, top = 52.dp, bottom = 26.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    TopBarCircleAction(
                        onClick = { /* Action Profil */ },
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
                        city = selectedCity,
                        containerColor = chipColor,
                        onCitySelected = { selectedCity = it }
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TopBarCircleAction(
                        onClick = { /* Action Scanner */ },
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
                            onClick = { /* Action Notif */ },
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
                                .background(Color(0xFFFF4B4B), CircleShape)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))
            BalanceCard(isVisible = isBalanceVisible, onToggle = onToggleVisibility)
        }
    }
}

@Composable
fun LocationChip(
    city: String,
    containerColor: Color,
    onCitySelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val cities = listOf("Bujumbura", "Gitega", "Ngozi", "Rumonge")

    Box {
        Row(
            modifier = Modifier
                .background(containerColor, RoundedCornerShape(22.dp))
                .clickable { expanded = true }
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .background(Color.White.copy(alpha = 0.18f), RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = city.take(1), color = Color.White, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = city, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(Icons.Default.KeyboardArrowDown, null, tint = Color.White)
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            cities.forEach { cityName ->
                DropdownMenuItem(
                    text = { Text(cityName) },
                    onClick = {
                        onCitySelected(cityName)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun BalanceCard(isVisible: Boolean, onToggle: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color(0xFF2B89E0)
    ) {
        Column(modifier = Modifier.padding(22.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("BALANCE", color = Color.White.copy(alpha = 0.9f))
                Row(
                    modifier = Modifier
                        .background(Color.White.copy(alpha = 0.13f), RoundedCornerShape(16.dp))
                        .clickable { onToggle() }
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = if (isVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(text = if (isVisible) "Hide" else "Show", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = if (isVisible) "1.250.000 BIF" else "• • • • • • • • BIF",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun OperationsBody() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 24.dp)
    ) {
        SectionTitle("CLIENT OPERATIONS")
        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OperationCard(Modifier.weight(1f), Icons.Default.ArrowDownward, "Dépôt")
            OperationCard(Modifier.weight(1f), Icons.Default.ArrowUpward, "Retrait")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OperationCard(Modifier.weight(1f), Icons.Default.Send, "Envoyer\nde l'arg...")
            OperationCard(Modifier.weight(1f), Icons.Default.SimCard, "Crédit\ntélépho...")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OperationCard(Modifier.weight(1f), Icons.Default.Description, "Forfait de\ndonnées")
            OperationCard(Modifier.weight(1f), Icons.Default.ReceiptLong, "Payer des\nfactures")
        }

        Spacer(modifier = Modifier.height(18.dp))
        FullWidthActionCard(Icons.Default.PersonAddAlt1, "Enregistrer un utilisateur")
        Spacer(modifier = Modifier.height(26.dp))
        SectionTitle("AGENT OPERATIONS")
        Spacer(modifier = Modifier.height(20.dp))
        FullWidthActionCard(Icons.Default.SwapHoriz, "Transferts entre agents")
        Spacer(modifier = Modifier.height(22.dp))
    }
}

@Composable
fun OperationCard(modifier: Modifier = Modifier, icon: ImageVector, title: String) {
    Surface(
        modifier = modifier.height(108.dp).clickable { /* Clic */ },
        shape = RoundedCornerShape(22.dp),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(icon, null, tint = Color(0xFF1D66C2), modifier = Modifier.size(28.dp))
            Text(title, color = Color(0xFF111111), fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun FullWidthActionCard(icon: ImageVector, title: String) {
    Surface(
        modifier = Modifier.fillMaxWidth().clickable { /* Clic */ },
        shape = RoundedCornerShape(22.dp),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 28.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, null, tint = Color(0xFF2F5FD1), modifier = Modifier.size(30.dp))
            Spacer(modifier = Modifier.width(18.dp))
            Text(title, Modifier.weight(1f), color = Color(0xFF111111), fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            Icon(Icons.Default.ArrowForwardIos, null, tint = Color(0xFF9A9A9A), modifier = Modifier.size(18.dp))
        }
    }
}

@Composable
fun BottomNavigationBar(selectedTab: String, onTabSelected: (String) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 12.dp),
        shape = RoundedCornerShape(28.dp),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val items = listOf(
                Icons.Default.Home to "Home",
                Icons.Default.Paid to "Earnings",
                Icons.Default.SwapHoriz to "History",
                Icons.Default.AccountBalanceWallet to "My Wallet"
            )

            items.forEach { (icon, label) ->
                BottomNavItem(
                    icon = icon,
                    label = label,
                    selected = selectedTab == label,
                    onClick = { onTabSelected(label) }
                )
            }
        }
    }
}

@Composable
fun BottomNavItem(icon: ImageVector, label: String, selected: Boolean, onClick: () -> Unit) {
    val selectedBg = if (selected) Color(0xFFE7EEF6) else Color.Transparent
    val selectedColor = if (selected) Color(0xFF2490FF) else Color(0xFF151515)

    Column(
        modifier = Modifier
            .background(selectedBg, RoundedCornerShape(22.dp))
            .clickable { onClick() }
            .padding(horizontal = 18.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, null, tint = selectedColor, modifier = Modifier.size(28.dp))
        if (selected) {
            Text(label, color = selectedColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun TopBarCircleAction(onClick: () -> Unit, containerColor: Color, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.size(52.dp).background(containerColor, CircleShape).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) { content() }
}

@Composable
fun SectionTitle(title: String) {
    Text(title, color = Color(0xFF8C9099), fontSize = 14.sp, fontWeight = FontWeight.SemiBold, letterSpacing = 1.sp)
}

@Preview(showBackground = true)
@Composable
fun BalanceScreenPreview() {
    HappyBirthdayTheme { BalanceScreen() }
}

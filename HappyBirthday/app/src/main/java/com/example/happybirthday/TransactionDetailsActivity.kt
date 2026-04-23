package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

// Une "data class" est comme un formulaire pré-rempli pour une transaction
data class TransactionData(
    val type: String,
    val code: String,
    val status: String,
    val source: String,
    val time: String,
    val senderName: String,
    val senderNumber: String,
    val receiverName: String,
    val receiverNumber: String,
    val amount: String,
    val remarks: String
)

class TransactionDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // Pour l'instant, on simule une transaction.
                // Dans le futur, on récupérera ces données depuis l'Intent.
                val fakeTransaction = TransactionData(
                    type = "Agent - Wallet cashin",
                    code = "262190218936",
                    status = "done",
                    source = "Asyst Wallet",
                    time = "22/04/2026 09:36",
                    senderName = "CHEZ-CARINE",
                    senderNumber = "0999999999",
                    receiverName = "NDIKUMANA YOHANA",
                    receiverNumber = "09506569666",
                    amount = "1,000",
                    remarks = "Abc"
                )
                TransactionDetailsScreen(data = fakeTransaction, onBack = { finish() })

            }
        }
    }
}
@Composable
fun TransactionDetailsScreen(data: TransactionData, onBack: () -> Unit) {
    var selectedTab by remember { mutableStateOf("History") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        // Le fond est le même bleu très clair que ton écran History
        containerColor = Color(0xFFE9F1F8),
                bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            // --- 1. TOP BAR PERSONNALISÉE ---
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 20.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Bouton retour rond
                Surface(
                    modifier = Modifier.size(45.dp).clickable { onBack() },
                    shape = CircleShape,
                    color = Color.White, // Couleur du bouton
                    shadowElevation = 1.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Retour",
                            modifier = Modifier.size(18.dp),
                            tint = Color.Black
                        )
                    }
                }

                // Titre centré
                Text(
                    text = "Transaction Details",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                // Un espace vide pour centrer le titre (car il n'y a pas d'action à droite)
                Spacer(modifier = Modifier.size(45.dp))
            }

            Spacer(modifier = Modifier.height(50.dp))

            // --- 2. TITRE DE SECTION ---
            Text(
                text = "Transaction History Details",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                textAlign = TextAlign.Center,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(40.dp))

            // --- 3. LA LISTE DES DÉTAILS ---
            // On utilise une Column normale pour simplifier, c'est peu de données
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp) // Espace égal entre chaque ligne
            ) {
                // On utilise un composant réutilisable pour chaque ligne
                DetailItem(label = "Transaction Type", value = data.type, valueFontWeight = FontWeight.Bold)
                DetailItem(label = "Transaction Code", value = data.code, icon = Icons.Default.HelpOutline, valueFontWeight = FontWeight.Bold)
                DetailItem(label = "Transaction Status", value = data.status, valueFontWeight = FontWeight.Bold)
                DetailItem(label = "Source Money", value = data.source, valueFontWeight = FontWeight.Bold)
                DetailItem(label = "Time", value = data.time, valueFontWeight = FontWeight.Bold)

                // Petit espace pour séparer les infos de la transaction des infos de personnes
                Spacer(modifier = Modifier.height(4.dp))

                DetailItem(label = "Sender Name", value = data.senderName, valueFontWeight = FontWeight.Bold)
                DetailItem(label = "Total", value = "${data.amount} BIF", valueFontWeight = FontWeight.Bold)
                DetailItem(label = "Sender Number", value = data.senderNumber, valueFontWeight = FontWeight.Bold)
                DetailItem(label = "Receiver Name", value = data.receiverName, valueFontWeight = FontWeight.Bold)
                DetailItem(label = "Receiver Number", value = data.receiverNumber, icon = Icons.Default.HelpOutline, valueFontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(4.dp))

                // Les montants sont en gras
                DetailItem(label = "Amount", value = "${data.amount} BIF", valueFontWeight = FontWeight.Bold)
                DetailItem(label = "Total", value = "${data.amount} BIF", valueFontWeight = FontWeight.Bold)
                DetailItem(label = "Remarks", value = data.remarks,valueFontWeight = FontWeight.Bold)
                DetailItem(label = "Total", value = "${data.amount} BIF", valueFontWeight = FontWeight.Bold )
            }
        }
    }
}
// --- Étape 3 : Le composant réutilisable pour chaque ligne de détail ---
@Composable
fun DetailItem(
    label: String,
    value: String,
    valueFontWeight: FontWeight = FontWeight.Normal,
    icon: ImageVector? = null // Paramètre optionnel pour l'icône Help
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Le label (gris clair)
        Text(
            text = label,
            fontSize = 15.sp,
            color = Color(0xFF919191), // Un gris clair similaire à ton image
            fontWeight = FontWeight.Normal
        )

        // La valeur (noir) et l'éventuelle icône
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = value,
                fontSize = 15.sp,
                color = Color.Black,
                fontWeight = valueFontWeight
            )

            // Si une icône est fournie, on l'affiche juste après la valeur
            if (icon != null) {
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Black
                )
            }
        }
    }
}

// --- Étape 4 : L'aperçu (Preview) ---
@Preview(showBackground = true)
@Composable
fun TransactionDetailsPreview() {
    HappyBirthdayTheme {
        val fakeTransaction = TransactionData(
            type = "Agent - Wallet cashin", code = "262190218936", status = "done", source = "Asyst Wallet", time = "22/04/2026 09:36",
            senderName = "CHEZ-CARINE", senderNumber = "0999999999", receiverName = "NDIKUMANA YOHANA", receiverNumber = "09506569666",
            amount = "1,000", remarks = "Abc"
        )
        TransactionDetailsScreen(data = fakeTransaction, onBack = {})
    }
}
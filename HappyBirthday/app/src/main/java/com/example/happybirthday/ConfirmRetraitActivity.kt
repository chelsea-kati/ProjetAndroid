package com.example.happybirthday

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class ConfirmRetraitActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val receiverName = intent.getStringExtra("RECEIVER_NAME") ?: "N/A"
        val phone = intent.getStringExtra("MOBILE_NUMBER") ?: "N/A"
        val amount = intent.getStringExtra("AMOUNT") ?: "0"
        
        setContent {
            HappyBirthdayTheme {
                ConfirmRetraitActivityScreen(
                    receiverName = receiverName,
                    mobileNumber = phone,
                    amount = amount,
                    onBack = { finish() }
                )
            }
        }
    }
}

@Composable
fun ConfirmRetraitActivityScreen(
    receiverName: String,
    mobileNumber: String,
    amount: String,
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    var showSuccessDialog by remember { mutableStateOf(false) }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        // Retour à l'accueil (BalanceActivity)
                        val intent = Intent(context, BalanceActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // Nettoie la pile d'activités
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFDDE2E8), // Gris clair comme sur l'image
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text("RETOUR SUR L'ACCUEIL", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            },
            title = { Text("Message", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
            text = { 
                Text(
                    "Cash out request completed. Please ask customer to accept in Subscriber App",
                    fontSize = 15.sp,
                    color = Color.Gray
                ) 
            },
            shape = RoundedCornerShape(28.dp),
            containerColor = Color.White
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFE9F1F8)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // --- BOUTON RETOUR ---
                Row(modifier = Modifier.fillMaxWidth()) {
                    Surface(
                        modifier = Modifier.size(45.dp).clickable { onBack() },
                        shape = CircleShape, color = Color.White
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.ArrowBackIosNew, "Retour", modifier = Modifier.size(18.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                // --- ICÔNE DE TRANSFERT (Cercle rose) ---
                Surface(
                    modifier = Modifier.size(90.dp),
                    shape = CircleShape,
                    color = Color(0xFFF2E7FE) // Rose/Violet très clair
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.SwapHoriz,
                            contentDescription = null,
                            tint = Color(0xFFD81B60),
                            modifier = Modifier.size(45.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Détail du transfert",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(30.dp))

                // --- CARTE DE DÉTAILS ---
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 1.dp
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        DetailRowTransfert(label = "Numéro de téléphone", value = mobileNumber)
                        DetailRowTransfert(label = "Nom", value = receiverName)
                        DetailRowTransfert(label = "Montant", value = "$amount BIF")
                        DetailRowTransfert(label = "Cotisation du bénéficiaire", value = "0 BIF")
                        
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            thickness = 0.5.dp, 
                            color = Color.LightGray.copy(alpha = 0.5f)
                        )
                        
                        DetailRowTransfert(label = "Total", value = "$amount BIF", isBoldValue = true)
                        DetailRowTransfert(label = "Temps expiré", value = "24/04/2026 11:00")
                    }
                }
            }

            // --- BOUTON CONFIRMER (Pill shape) ---
            Button(
                onClick = { showSuccessDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0E5BA9))
            ) {
                Text("CONFIRMER", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}

@Composable
fun DetailRowTransfert(label: String, value: String, isBoldValue: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = Color.Gray, fontSize = 14.sp, modifier = Modifier.weight(1.2f))
        Text(
            text = value,
            color = Color.Black,
            fontSize = 15.sp,
            fontWeight = if (isBoldValue) FontWeight.Bold else FontWeight.SemiBold,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmRetraitPreview() {
    HappyBirthdayTheme {
        ConfirmRetraitActivityScreen("NDIKUMANA YOHANA", "09506569666", "1,000")
    }
}

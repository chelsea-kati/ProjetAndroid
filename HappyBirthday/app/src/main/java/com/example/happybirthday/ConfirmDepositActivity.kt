package com.example.happybirthday

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class ConfirmDepositActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // C'EST ICI QUE LES VRAIES DONNÉES SONT RÉCUPÉRÉES DU DÉPÔT
        val receiverName = intent.getStringExtra("RECEIVER_NAME") ?: "N/A"
        val phone = intent.getStringExtra("MOBILE_NUMBER") ?: "N/A"
        val amount = intent.getStringExtra("AMOUNT") ?: "0"
        val fee = "0"
        val total = amount


        setContent {
            HappyBirthdayTheme {
                ConfirmDepositActivityScreen(
                    receiverName = receiverName,
                    mobileNumber = phone,
                    amount = amount,
                    fee = fee,
                    total = total,
                    onBack = { finish() }
                )
            }
        }
    }
}

@Composable
fun ConfirmDepositActivityScreen(
    receiverName: String,
    mobileNumber: String,
    amount: String,
    fee: String,
    total: String,
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current // <-- Ajoutez cette ligne au début de la fonction
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFE9F1F8)
    ) { paddingValues ->
        // On utilise un Box pour pouvoir épingler le bouton en bas
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
                // --- 1. BOUTON RETOUR ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Surface(
                        modifier = Modifier
                            .size(45.dp)
                            .clickable { onBack() },
                        shape = CircleShape,
                        color = Color.White
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(Icons.Default.ArrowBackIosNew, "Retour", modifier = Modifier.size(18.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // --- 2. ICÔNE CENTRALE ---
                Surface(
                    modifier = Modifier.size(80.dp),
                    shape = CircleShape,
                    color = Color(0xFFFCE4EC)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = null,
                            tint = Color(0xFFE91E63),
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // --- 3. TITRE ---
                Text(
                    text = "Cash-in Preview",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(30.dp))

                // --- 4. CARTE DE DÉTAILS ---
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                modifier = Modifier.size(32.dp),
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFF0D47A1)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.Description, null, tint = Color.White, modifier = Modifier.size(16.dp))
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("Cash-in Details", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
                        Spacer(modifier = Modifier.height(16.dp))

                        DetailRow(label = "Receiver's Name", value = receiverName)
                        DetailRow(label = "Mobile Number", value = mobileNumber)
                        DetailRow(label = "Amount", value = "$amount BIF")
                        DetailRow(label = "Fee", value = "$fee BIF")

                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
                        Spacer(modifier = Modifier.height(16.dp))

                        DetailRow(label = "Total", value = "$total BIF", isTotal = true)
                    }
                }
            }

            // --- 5. BOUTON CONFIRM (Épinglé en bas) ---
            Button(
                onClick = {
                    // ACTION : Aller vers la page de succès
                    val intent = Intent(context, SuccessActivity::class.java).apply {
                        putExtra("AMOUNT", amount)
                        putExtra("PHONE", mobileNumber)
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter), // Aligné en bas du Box
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1))
            ) {
                Text("Confirm", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String, isTotal: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = Color.Gray, fontSize = 14.sp)
        Text(
            text = value,
            color = Color.Black,
            fontSize = if (isTotal) 18.sp else 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// CETTE PREVIEW NE SERT QU'À VOIR LE DESIGN DANS ANDROID STUDIO
@Preview(showBackground = true)
@Composable
fun ConfirmDepositPreview() {
    HappyBirthdayTheme {
        ConfirmDepositActivityScreen(
            receiverName = "APERÇU NOM",
            mobileNumber = "00000000",
            amount = "1,000",
            fee = "0",
            total = "1,000",
            onBack = {}
        )
    }
}

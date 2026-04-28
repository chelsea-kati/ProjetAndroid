package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme
import android.content.Intent
import androidx.compose.ui.platform.LocalContext

class RetraitActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super .onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                RetraitActivityScreen (onBack = {finish ()})
            }
        }
    }
}

@Composable
fun RetraitActivityScreen(onBack: () -> Unit = {}) {
    // --- 1. ÉTATS (STATE) ---
    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var remarks by remember { mutableStateOf("") }

    var recipientName by remember { mutableStateOf<String?>(null) }
    
    LaunchedEffect(phoneNumber) {
        if(phoneNumber.length == 10){
            recipientName = "NDIKUMANA YOHANA" // Mis à jour selon ton image
        } else {
            recipientName = null
        }
    }
    
    val isFormValid = phoneNumber.length == 10 && amount.isNotEmpty() && recipientName != null

    // --- 2. INTERFACE UTILISATEUR (UI) ---
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFE9F1F8)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            // --- TOP BAR ---
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(45.dp).clickable { onBack() },
                    shape = CircleShape, color = Color.White, shadowElevation = 1.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            Icons.Default.ArrowBackIosNew,
                            "Retour",
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Text(
                    text = "Demande du retrait", // Titre mis à jour
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.size(45.dp))
            }

            // Champ Numéro
            RoundedTextField(
                value = phoneNumber,
                onValueChange = { if (it.length <= 10) phoneNumber = it },
                placeholder = "Numéro de téléphone du bénéficiaire (*)",
                icon = Icons.Default.Phone,
                keyboardType = KeyboardType.Phone
            )

            // Bloc Destinataire
            AnimatedVisibility(
                visible = recipientName != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                recipientName?.let { name ->
                    RecipientField(name = name)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            // Champ Montant
            RoundedTextField(
                value = amount,
                onValueChange = { amount = it },
                placeholder = "Montant (*)",
                trailingText = "BIF",
                keyboardType = KeyboardType.Number
            )

            Spacer(modifier = Modifier.height(16.dp))
            
            // Champ Remarques
            RoundedTextField(
                value = remarks,
                onValueChange = { remarks = it },
                placeholder = "Remarques (facultatif)",
                singleLine = false,
                modifier = Modifier.height(150.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // --- BOUTON CONTINUE ---
            Button(
                onClick = {
                    // Création de l'Intent avec les données dynamiques
                    val intent = Intent(context, ConfirmRetraitActivity::class.java).apply {
                        putExtra("RECEIVER_NAME", recipientName ?: "N/A")
                        putExtra("MOBILE_NUMBER", phoneNumber)
                        putExtra("AMOUNT", amount)
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth().height(55.dp).padding(bottom = 16.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) Color(0xFF0E5BA9) else Color(0xFFC7CDD3),
                    contentColor = Color.White
                ),
                enabled = isFormValid
            ) {
                Text(text = "Continue", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RetraitPreview() {
    HappyBirthdayTheme {
        RetraitActivityScreen(onBack = {})
    }
}

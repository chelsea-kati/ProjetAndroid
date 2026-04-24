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

class DepositActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super .onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                DepositActivityScreen (onBack = {finish ()})
            }
        }
    }

}
@Composable
fun DepositActivityScreen(onBack: () -> Unit = {}) {
    // --- 1. ÉTATS (STATE) ---
    var phoneNumber by remember {mutableStateOf(" ")}
    var amount by remember {mutableStateOf(" ")}
    var remarks by remember {mutableStateOf(" ")}

    // État simulé pour le destinataire. Plus tard, cela viendra d'un API call.
    var recipientName by remember { mutableStateOf<String?>(null) }
     // Logique : Si le numéro a 10 chiffres, on simule avoir trouvé le destinataire
    LaunchedEffect(phoneNumber) {
        if(phoneNumber.length ==10){
            //simulation de l'appel
            recipientName = " NDIKUMANA JOSIANE"
        } else{
            recipientName = null
        }
    }
    // Le bouton est activé seulement si tout est rempli
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
            // --- 2. TOP BAR (Similaire à Details) ---
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
                    text = "Dépôt",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.size(45.dp)) // Pour centrer le titre
            }
            // --- 3. LES CHAMPS DE SAISIE ---

            // Champ Numéro de téléphone
            RoundedTextField(
                value = phoneNumber,
                onValueChange = { if (it.length <= 10) phoneNumber = it }, // Limite à 10 chiffres
                placeholder = "Numéro de téléphone du bénéficiaire (*)",
                icon = Icons.Default.Phone,
                keyboardType = KeyboardType.Phone
            )
            // --- 4. CHAMP DYNAMIQUE RECIPIENT (AnimatedVisibility) ---
            // Ce bloc apparaît/disparaît fluidement
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
            // Champ Remarques (Plus grand)
            RoundedTextField(
                value = remarks,
                onValueChange = { remarks = it },
                placeholder = "Remarques (facultatif)",
                singleLine = false,
                modifier = Modifier.height(150.dp) // Hauteur comme sur l'image
            )

            Spacer(modifier = Modifier.weight(1f)) // Pousse le bouton vers le bas
            // --- 5. BOUTON SUIVANT ---
            Button(
                onClick = { /* Action Suivant */ },
                modifier = Modifier.fillMaxWidth().height(55.dp).padding(bottom = 16.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFormValid) Color(0xFF0E5BA9) else Color(0xFFC7CDD3), // Bleu si valide, Gris sinon
                    contentColor = Color.White
                ),
                enabled = isFormValid // Désactivé si formulaire incomplet
            ) {
                Text(text = "Suivant", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }

        }

    }
}

// --- COMPOSANTS UI RÉUTILISABLES ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    trailingText: String? = null,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp), // Arrondi comme sur l'image
        color = Color.White,
        shadowElevation = 0.5.dp // Très légère ombre
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = placeholder, color = Color.DarkGray.copy(alpha = 0.7f), // Plus sombre et visible
                 fontSize = 14.sp,
                fontWeight = FontWeight.Medium) },
            leadingIcon = icon?.let { { Icon(it, null, tint = Color.Gray) } },
            trailingIcon = trailingText?.let { { Text(it, color = Color.Gray, fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 8.dp)) } },
            singleLine = singleLine,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = TextFieldDefaults.colors(),
            // Pour aligner le texte en haut dans le champ Remarques multi-ligne
            textStyle = LocalTextStyle.current.copy(textAlign = if(singleLine) TextAlign.Start else TextAlign.Start)
        )
    }
}

@Composable
fun RecipientField(name: String) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            shadowElevation = 0.5.dp
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(35.dp),
                    tint = Color(0xFF0E5BA9) // Bleu Waangu
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = "RECIPIENT", color = Color.Gray, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Text(text = name, color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DepositPreview() {
    HappyBirthdayTheme {
        DepositActivityScreen(onBack = {})
    }
}
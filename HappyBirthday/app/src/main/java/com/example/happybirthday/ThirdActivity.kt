package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme


class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TransactionScreen(onBack = { finish() })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(onBack: () -> Unit = {}) {
    var transactionCode by remember { mutableStateOf("") }
    // Voici ta liste de transactions (actuellement vide)
    val transactions = remember { mutableStateListOf<String>() }
    // Liste des titres pour tes 3 lignes
    val dateItems = listOf(
        "Date de début" to "10/04/2025",
        "Date de fin" to "14/01/2024",
        "Tout" to "13/06/2022")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Transactions de la boutique") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Retour"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // --- BLOC DU HAUT (Dates + Champ de saisie) ---
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    // Affichage des 3 lignes (Date de début, fin, validation)
                    dateItems.forEach { (label, date) ->
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        )
                        {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 1
                            )
                            Surface(
                                modifier = Modifier.fillMaxWidth(),
                                shape = MaterialTheme.shapes.small,
                                color = MaterialTheme.colorScheme.surfaceVariant
                            ) {
                                Text(
                                    text = date, // Ici la valeur de la date
                                    modifier = Modifier.padding(12.dp),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }

                // --- CHAMP DE SAISIE DU BAS ---
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    //Text(
                    // text = "Code de transaction",
                    // style = MaterialTheme.typography.titleMedium
                    //)
                    OutlinedTextField(
                        value = transactionCode,
                        onValueChange = { transactionCode = it },
                        placeholder = { Text(text = "Entrez le numero du telephone a rechercher") }, // Placeholder ajouté ici
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                )
                {
                    if (transactions.isEmpty()) {
                        item {
                            Text(
                                text = "Liste vide",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }


                    } else {
                        items(transactions) { transaction ->
                            // Comment afficher chaque élément si la liste n'est pas vide
                            Text(
                                text = "Transaction: $transaction",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }

                // --- BOUTON CHERCHER TOUT EN BAS ---
//            Button(
//                onClick = { /* Action ici */ },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(56.dp)
//            ) {
//                Text(text = "Chercher")
//            }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TransactionScreenPreview() {
    HappyBirthdayTheme {
        TransactionScreen()
    }
}
package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                // Surface est maintenant reconnu grâce à l'import
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // APPEL DE TA FONCTION ICI pour qu'elle s'affiche sur le téléphone
                    ForthScreen()
                }
            }
        }
    }
}

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    //Surface(color = Color.Magenta){
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            Row(modifier = Modifier.padding(16.dp)) {

            }
            Text(
                text = message,
               // modifier = modifier.padding(5.dp), // Utilise le modifier passé en paramètre
                fontSize = 50.sp,
                lineHeight = 100.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier // <-- Utilise un NOUVEAU Modifier ici
                    .padding(20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = from,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }
    }

//}
@Composable
fun GreetingImage(message: String, from : String, modifier: Modifier = Modifier)
{
    val image = painterResource(R.drawable.papillon)
    Box(modifier = modifier) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }

}
@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        GreetingImage(message = "Happy Birthday Chelsea!" , from = "from Emma")
    }
}
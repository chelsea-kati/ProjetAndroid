package com.example.profileactivity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profileactivity.R

@Composable
fun ProfileActivity(
    cornerShapeRadius: Dp = 16.dp,
    contentPadding: Dp = 16.dp,
    imageSize: Dp = 80.dp,
    primaryColor: Color = Color(0xFF283542),
    secondaryColor: Color = Color(0xFF05D79B),
    tertiaryColor: Color = Color.White,

    // Utilisation d'une icône par défaut pour éviter les erreurs si ai_face n'existe pas
    imagePainter: Painter = painterResource(id = R.drawable.photo),
    imageShape: Shape = CircleShape,
    titleText: String = "Ange Louan",
    titleFontSize: TextUnit = 20.sp,
    titleTextColor: Color = tertiaryColor,
    subtitleText: String = "Software Engineer",
    subtitleFontSize: TextUnit = 16.sp,
    subtitleTextColor: Color = Color(0xFF9E9FA0),
    headerBackgroundColor: Color = primaryColor,

    icon1Painter: Painter = painterResource(id = R.drawable.ic_twitter),
    icon2Painter: Painter = painterResource(id = R.drawable.ic_github),
    icon3Painter: Painter = painterResource(id = R.drawable.ic_linkedin),
    icon4Painter: Painter = painterResource(id = R.drawable.ic_medium),
    iconBackgroundColor: Color = Color(0xFFF0F0F0),
    iconTintColor: Color = primaryColor,

    button1Text: String = "Follow",
    button1TextColor: Color = primaryColor,
    button1ContainerColor: Color = Color.Transparent,
    button2Text: String = "Contact",
    button2TextColor: Color = tertiaryColor,
    button2TextContainerColor: Color = primaryColor,
    buttonsTextSize: TextUnit = 16.sp,
    buttonsCornerShape: Shape = RoundedCornerShape(12.dp)
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(cornerShapeRadius),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.background(Color.White)) {
            // Section Entête
            ProfileHeader(
                imagePainter = imagePainter,
                imageSize = imageSize,
                imageShape = imageShape,
                titleText = titleText,
                titleFontSize = titleFontSize,
                titleTextColor = titleTextColor,
                subtitleText = subtitleText,
                subtitleFontSize = subtitleFontSize,
                subtitleTextColor = subtitleTextColor,
                backgroundColor = headerBackgroundColor,
                contentPadding = contentPadding
            )

            // Section Icônes Sociales
            SocialMediaIcons(
                icon1Painter = icon1Painter,
                icon2Painter = icon2Painter,
                icon3Painter = icon3Painter,
                icon4Painter = icon4Painter,
                iconBackgroundColor = iconBackgroundColor,
                iconTintColor = iconTintColor,
                contentPadding = contentPadding
            )

            // Section Boutons d'action
            ProfileActionButtons(
                textButton1 = button1Text,
                textColorButton1 = button1TextColor,
                containerColorButton1 = button1ContainerColor,
                textButton2 = button2Text,
                textColorButton2 = button2TextColor,
                containerColorButton2 = button2TextContainerColor,
                buttonsTextSize = buttonsTextSize,
                buttonsCornerShape = buttonsCornerShape,
                contentPadding = contentPadding
            )
        }
    }
}

@Composable
fun ProfileHeader(
    imagePainter: Painter,
    imageSize: Dp,
    imageShape: Shape,
    titleText: String,
    titleFontSize: TextUnit,
    titleTextColor: Color,
    subtitleText: String,
    subtitleFontSize: TextUnit,
    subtitleTextColor: Color,
    backgroundColor: Color,
    contentPadding: Dp
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = imagePainter,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(imageSize)
                .clip(imageShape),
               // .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = titleText, fontSize = titleFontSize, color = titleTextColor)
        Text(text = subtitleText, fontSize = subtitleFontSize, color = subtitleTextColor)
    }
}

@Composable
fun SocialMediaIcons(
    icon1Painter: Painter,
    icon2Painter: Painter,
    icon3Painter: Painter,
    icon4Painter: Painter,
    iconBackgroundColor: Color,
    iconTintColor: Color,
    contentPadding: Dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = contentPadding),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val iconModifier = Modifier
            .size(40.dp)
            .background(iconBackgroundColor, CircleShape)
            .padding(8.dp)

        Icon(painter = icon1Painter, contentDescription = null, modifier = iconModifier, tint = iconTintColor)
        Icon(painter = icon2Painter, contentDescription = null, modifier = iconModifier, tint = iconTintColor)
        Icon(painter = icon3Painter, contentDescription = null, modifier = iconModifier, tint = iconTintColor)
        Icon(painter = icon4Painter, contentDescription = null, modifier = iconModifier, tint = iconTintColor)
    }
}

@Composable
fun ProfileActionButtons(
    textButton1: String,
    textColorButton1: Color,
    containerColorButton1: Color,
    textButton2: String,
    textColorButton2: Color,
    containerColorButton2: Color,
    buttonsTextSize: TextUnit,
    buttonsCornerShape: Shape,
    contentPadding: Dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = contentPadding)
            .padding(bottom = contentPadding),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedButton(
            onClick = { },
            modifier = Modifier.weight(1f),
            shape = buttonsCornerShape,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = textColorButton1)
        ) {
            Text(text = textButton1, fontSize = buttonsTextSize)
        }
        Button(
            onClick = { },
            modifier = Modifier.weight(1f),
            shape = buttonsCornerShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColorButton2,
                contentColor = textColorButton2
            )
        ) {
            Text(text = textButton2, fontSize = buttonsTextSize)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileActivityPreview() {
    ProfileActivity()
}
package com.example.ville_compose.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Palette de couleurs
object AppColors {
    val Primary = Color(0xFF6B5CE7)
    val PrimaryVariant = Color(0xFF5847C7)
    val Secondary = Color(0xFF4ECDC4)
    val Background = Color(0xFFF8F9FA)
    val Surface = Color(0xFFFFFFFF)
    val TextPrimary = Color(0xFF2D3436)
    val TextSecondary = Color(0xFF636E72)
    val Border = Color(0xFFE0E0E0)
    val Success = Color(0xFF00B894)
    val Rating = Color(0xFFFFA502)
}

// Formes
object AppShapes {
    val Small = RoundedCornerShape(8.dp)
    val Medium = RoundedCornerShape(12.dp)
    val Large = RoundedCornerShape(16.dp)
    val ExtraLarge = RoundedCornerShape(24.dp)
}

// Typographie
object AppTypography {
    val Heading1 = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = AppColors.TextPrimary
    )

    val Heading2 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        color = AppColors.TextPrimary
    )

    val Body = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = AppColors.TextPrimary
    )

    val BodySecondary = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = AppColors.TextSecondary
    )

    val Caption = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = AppColors.TextSecondary
    )

    val Button = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.White
    )
}

// Espacements
object AppSpacing {
    val ExtraSmall = 4.dp
    val Small = 8.dp
    val Medium = 16.dp
    val Large = 24.dp
    val ExtraLarge = 32.dp
}

private val LightColorScheme = lightColorScheme(
    primary = AppColors.Primary,
    onPrimary = Color.White,
    secondary = AppColors.Secondary,
    background = AppColors.Background,
    surface = AppColors.Surface,
    onSurface = AppColors.TextPrimary
)

@Composable
fun VilleComposeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        shapes = Shapes(
            small = AppShapes.Small,
            medium = AppShapes.Medium,
            large = AppShapes.Large
        ),
        content = content
    )
}
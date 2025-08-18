package com.plcoding.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.crypto.presentation.models.DisplayableNumber
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun PriceChange(
    change : DisplayableNumber,
    modifier: Modifier = Modifier
) {
    val backgroundColor : Color = if(change.value < 0.0) Color.Red else Color.Green
    val contentColor : Color = if(change.value < 0.0) Color.White else Color.Black
    Row (
        modifier = Modifier.clip(RoundedCornerShape(100f))
            .background(backgroundColor)
            .padding(4.dp)
        ,

        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if(change.value < 0.0)
                Icons.Default.KeyboardArrowDown
            else
                Icons.Default.KeyboardArrowUp,
            contentDescription = null,
            tint = contentColor
        )
        Text(
            text = change.formatted + "%",
            fontSize = 14.sp,
            color = contentColor
        )
    }
}

@Preview
@Composable
fun PriceChangePreview()
{
    CryptoTrackerTheme {
        PriceChange(
            change = DisplayableNumber(
                value = 2.52,
                formatted = "2.52"
            )
        )
    }
}
@Preview
@Composable
fun PriceChangePreviewNeg()
{
    PriceChange(
        change = DisplayableNumber(
            value = -2.52,
            formatted = "-2.52"
        )
    )
}
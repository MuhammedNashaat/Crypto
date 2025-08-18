package com.plcoding.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.presentation.models.CoinUi
import com.plcoding.cryptotracker.crypto.presentation.models.toCoinUi
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinListItem(
    coinUi : CoinUi,
    onClick : () -> Unit,
    modifier: Modifier = Modifier
) {
    val contentColor : Color = if(isSystemInDarkTheme())
        Color.White
            else
        Color.Black
    Row (
        modifier = modifier
            .clickable (onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = coinUi.iconRes),
            contentDescription = coinUi.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(85.dp)
        )
        Column (
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = coinUi.symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
            Text(
                text = coinUi.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = contentColor
            )
        }
        Column (
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = coinUi.priceUsd.formatted + " $",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
            PriceChange(
                coinUi.changePercent24Hr
            )
        }
    }

}

@PreviewLightDark
@Composable
fun CoinListPreview() {
    CryptoTrackerTheme {
        CoinListItem(
            coinUi = previewCoinUi,
            onClick = {/*todo*/},
        )
    }
}

internal val previewCoin : Coin = Coin(
    id = "bitcoin",
    rank = 1,
    symbol = "BTC",
    name = "BitCoin",
    marketCapUsd = 11345478.079862315,
    priceUsd = 5745.2862,
    changePercent24Hr = -0.0461491427646531,
)

internal val previewCoinUi : CoinUi =  previewCoin.toCoinUi()
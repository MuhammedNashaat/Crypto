package com.plcoding.cryptotracker.crypto.presentation.coin_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.crypto.presentation.coin_list.CoinListState
import com.plcoding.cryptotracker.crypto.presentation.coin_list.components.previewCoinUi
import com.plcoding.cryptotracker.crypto.presentation.models.CoinUi
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinDetailScreen(
    state: CoinListState,
    modifier: Modifier = Modifier
){
    if(state.isLoading == true)
    {
        //loading
    }
    else if(state.selectedCoin != null)
    {
        val contentColor:Color = if(isSystemInDarkTheme())
            Color.White else Color.Black

        val coin: CoinUi= state.selectedCoin
        Column(
            modifier = modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = coin.iconRes),
                contentDescription = coin.name,
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = coin.name,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = contentColor
            )
            Text(
                text = coin.symbol,
                fontSize = 30.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                color = contentColor
            )
        }
    }

}

@PreviewLightDark
@Composable
fun CoinDetailScreenPreview()
{
    CryptoTrackerTheme {
        CoinDetailScreen(
            state = CoinListState(
                isLoading = false,
                coins = (1..10).map{
                    previewCoinUi.copy(id = it.toString())
                },
                selectedCoin = previewCoinUi
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}
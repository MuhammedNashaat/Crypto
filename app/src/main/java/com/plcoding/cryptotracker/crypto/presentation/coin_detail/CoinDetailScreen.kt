@file:OptIn(ExperimentalLayoutApi::class)

package com.plcoding.cryptotracker.crypto.presentation.coin_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.cryptotracker.R
import com.plcoding.cryptotracker.crypto.presentation.coin_detail.components.InfoCard
import com.plcoding.cryptotracker.crypto.presentation.coin_list.CoinListState
import com.plcoding.cryptotracker.crypto.presentation.coin_list.components.previewCoinUi
import com.plcoding.cryptotracker.crypto.presentation.models.CoinUi
import com.plcoding.cryptotracker.crypto.presentation.models.DisplayableNumber
import com.plcoding.cryptotracker.crypto.presentation.models.toDisplayableNumber
import com.plcoding.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinDetailScreen(
    state: CoinListState,
    modifier: Modifier = Modifier
){
    if(state.isLoading)
    {
        Box (
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
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
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                InfoCard(
                    title = stringResource(id = R.string.market_cap),
                    formattedText = "$ ${coin.marketCapUsd.formatted}",
                    icon = ImageVector.vectorResource(id = R.drawable.stock)
                )
                InfoCard(
                    title = stringResource(id = R.string.price),
                    formattedText = "$ ${coin.priceUsd.formatted}",
                    icon = ImageVector.vectorResource(id = R.drawable.dollar)
                )
                val absoluteChange = (coin.priceUsd.value * (coin.changePercent24Hr.value/100)).toDisplayableNumber()
               InfoCard(
                   title = stringResource(id = R.string.price_change24Hr),
                   formattedText = "$ ${absoluteChange.formatted}",
                   icon = if(coin.changePercent24Hr.value > 0.0) ImageVector.vectorResource(id = R.drawable.trending)
                        else ImageVector.vectorResource(id = R.drawable.trending_down),
                   contentColor = if(coin.changePercent24Hr.value > 0.0) Color.Green else Color.Red
               )
            }
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
package com.plcoding.cryptotracker.crypto.presentation.models

import android.annotation.SuppressLint
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.util.getDrawableIdForCoin

data class CoinUi(
    var id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int
)

data class DisplayableNumber(
    val value: Double,
    val formatted : String
)

fun Coin.toCoinUi():CoinUi
{
    val coinUi : CoinUi = CoinUi(
        id = id,
        rank = rank,
        symbol = symbol,
        name = name,
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        priceUsd = priceUsd.toDisplayableNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol) // made by philipp
    )
    return  coinUi
}

@SuppressLint("DefaultLocale")
fun Double.toDisplayableNumber():DisplayableNumber
{
    return DisplayableNumber(
        value = this,
        formatted = String.format("%.3f",this)
    )
}
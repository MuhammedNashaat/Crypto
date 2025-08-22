package com.plcoding.cryptotracker.crypto.data.mapppers

import com.plcoding.cryptotracker.crypto.data.networking.dto.CoinDto
import com.plcoding.cryptotracker.crypto.domain.Coin

fun CoinDto.toCoin():Coin{
    return Coin(
        id = this.id,
        rank = this.rank,
        symbol = this.symbol,
        name = this.name,
        marketCapUsd = this.marketCapUsd,
        priceUsd = this.priceUsd,
        changePercent24Hr = this.changePercent24Hr,
    )
}
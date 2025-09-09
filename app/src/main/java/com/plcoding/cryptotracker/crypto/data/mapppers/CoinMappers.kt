package com.plcoding.cryptotracker.crypto.data.mapppers

import com.plcoding.cryptotracker.crypto.data.networking.dto.CoinDto
import com.plcoding.cryptotracker.crypto.data.networking.dto.CoinHistoryDto
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.domain.CoinHistory
import java.time.Instant
import java.time.ZoneId

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

fun CoinHistoryDto.toCoinHistory():CoinHistory{
    return CoinHistory(
        priceUsd = this.priceUsd,
        time = Instant.ofEpochMilli(this.time).atZone(ZoneId.of("UTC")),
        date = this.date
    )
}
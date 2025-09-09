package com.plcoding.cryptotracker.crypto.domain
import com.plcoding.cryptotracker.crypto.core.domain.util.NetworkError
import com.plcoding.cryptotracker.crypto.core.domain.util.Result
import java.time.ZonedDateTime


interface CoinDataSource {
    suspend fun getCoin():Result<List<Coin>,NetworkError>
    suspend fun getCoinHistory(
        coinId: String,
        interval: String = "h6",
        start: ZonedDateTime,
        end: ZonedDateTime
    ):Result<List<CoinHistory>,NetworkError>
}
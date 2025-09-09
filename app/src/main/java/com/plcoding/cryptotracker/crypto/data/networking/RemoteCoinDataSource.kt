package com.plcoding.cryptotracker.crypto.data.networking

import com.plcoding.cryptotracker.BuildConfig
import com.plcoding.cryptotracker.crypto.core.data.networking.constructURL
import com.plcoding.cryptotracker.crypto.core.data.networking.safeCall
import com.plcoding.cryptotracker.crypto.core.domain.util.NetworkError
import com.plcoding.cryptotracker.crypto.core.domain.util.Result
import com.plcoding.cryptotracker.crypto.core.domain.util.map
import com.plcoding.cryptotracker.crypto.data.mapppers.toCoin
import com.plcoding.cryptotracker.crypto.data.mapppers.toCoinHistory
import com.plcoding.cryptotracker.crypto.data.networking.dto.CoinHistoryResponseDto
import com.plcoding.cryptotracker.crypto.data.networking.dto.CoinResponseDto
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.domain.CoinDataSource
import com.plcoding.cryptotracker.crypto.domain.CoinHistory
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(
    private val httpClient : HttpClient
) : CoinDataSource{
    val API_KEY: String = BuildConfig.API_KEY
    override suspend fun getCoin(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructURL("assets")
            ) {
                parameter("apiKey",API_KEY)
            }
        }.map { response -> response.data.map{it.toCoin()} }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        interval: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinHistory>, NetworkError> {

        //from ZonedTime to milliSec
        val startMillis = start.withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant().toEpochMilli()
        val endMillis = end.withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant().toEpochMilli()

        return safeCall<CoinHistoryResponseDto> {
            httpClient.get(
                urlString = constructURL("assets/$coinId/history")
            ){
                parameter("apiKey",API_KEY)
                parameter("interval",interval)
                parameter("start",startMillis)
                parameter("end",endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinHistory() }
        }
    }
}
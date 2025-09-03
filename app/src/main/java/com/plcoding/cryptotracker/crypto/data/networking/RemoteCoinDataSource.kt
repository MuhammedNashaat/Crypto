package com.plcoding.cryptotracker.crypto.data.networking

import com.plcoding.cryptotracker.crypto.core.data.networking.constructURL
import com.plcoding.cryptotracker.crypto.core.data.networking.safeCall
import com.plcoding.cryptotracker.crypto.core.domain.util.NetworkError
import com.plcoding.cryptotracker.crypto.core.domain.util.Result
import com.plcoding.cryptotracker.crypto.core.domain.util.map
import com.plcoding.cryptotracker.crypto.data.mapppers.toCoin
import com.plcoding.cryptotracker.crypto.data.networking.dto.CoinResponseDto
import com.plcoding.cryptotracker.crypto.domain.Coin
import com.plcoding.cryptotracker.crypto.domain.CoinDataSource
import com.plcoding.cryptotracker.crypto.domain.CoinHistory
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient : HttpClient
) : CoinDataSource{
    override suspend fun getCoin(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructURL("assets")
            )
        }.map { response -> response.data.map{it.toCoin()} }
    }

    override suspend fun getCoinHistory(): Result<List<CoinHistory>, NetworkError> {
        TODO("Not yet implemented")
    }
}
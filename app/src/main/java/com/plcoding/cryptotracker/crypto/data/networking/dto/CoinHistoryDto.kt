package com.plcoding.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinHistoryDto (
    val priceUsd: Double,
    val time: Long,
    val date: String
)
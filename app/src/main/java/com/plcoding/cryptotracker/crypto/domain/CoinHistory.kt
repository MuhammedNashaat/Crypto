package com.plcoding.cryptotracker.crypto.domain

import android.icu.util.TimeZone

data class CoinHistory (
    //val circulatingSupply: Long ,
    val priceUsd: Double,
    val time: TimeZone,
    val date: String
)
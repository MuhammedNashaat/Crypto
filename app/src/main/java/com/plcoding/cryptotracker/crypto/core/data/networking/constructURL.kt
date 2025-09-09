package com.plcoding.cryptotracker.crypto.core.data.networking

import com.plcoding.cryptotracker.BuildConfig

fun constructURL(url: String):String{
    val BASE_URL: String = BuildConfig.BASE_URL

    if (url.contains(BASE_URL))
        return url
    else if(url.startsWith("/")) //relative url like /assets
        return BASE_URL+url.drop(1)
    else
        return BASE_URL+url
}
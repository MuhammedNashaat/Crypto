package com.plcoding.cryptotracker.crypto.core.data.networking

import com.plcoding.cryptotracker.BuildConfig

fun constructURL(url: String):String{
    val API_KEY: String = BuildConfig.API_KEY
    val BASE_URL: String = BuildConfig.BASE_URL

    if (url.contains(BASE_URL))
        return url+"?apiKey="+API_KEY
    else if(url.startsWith("/")) //relative url like /assets
        return BASE_URL+url.drop(1)+"?apiKey="+API_KEY
    else
        return BASE_URL+url + "?apiKey=" + API_KEY
}
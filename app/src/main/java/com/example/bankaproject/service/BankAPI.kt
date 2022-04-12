package com.example.bankaproject.service

import com.example.bankaproject.model.BankaData
import io.reactivex.Single
import retrofit2.http.GET

interface BankAPI {

    //https://raw.githubusercontent.com/fatiha380/mockjson/main/bankdata
    //BASE_URL -> https://raw.githubusercontent.com/
    //EXT -> fatiha380/mockjson/main/bankdata

    @GET("fatiha380/mockjson/main/bankdata")
    fun getBankData() : Single<List<BankaData>>

}
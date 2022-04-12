package com.example.bankaproject.service

import com.example.bankaproject.model.BankaData
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BankAPIService {

    //https://raw.githubusercontent.com/fatiha380/mockjson/main/bankdata
    //BASE_URL -> https://raw.githubusercontent.com/
    //EXT -> fatiha380/mockjson/main/bankdata

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(BankAPI::class.java)

    fun getData() : Single<List<BankaData>> {
        return api.getBankData()
    }


}
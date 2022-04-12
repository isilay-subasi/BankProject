package com.example.bankaproject.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.bankaproject.model.BankaData
import com.example.bankaproject.service.BankAPIService
import com.example.bankaproject.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel (application: Application) : BaseViewModel(application){

    private val bankaApiServie = BankAPIService()

    private val disposable = CompositeDisposable()

    private var customPreferences = CustomSharedPreferences(getApplication())


    val datas = MutableLiveData<List<BankaData>>()
    val bankaError = MutableLiveData<Boolean>()
    val bankaLoading = MutableLiveData<Boolean>()


    fun refreshData(){
        getDataFromAPI()
    }


     fun showBankList(bankaList: List<BankaData>){



        datas.value=bankaList
        bankaLoading.value=false
        bankaError.value=false
    }


    private fun getDataFromAPI(){
        bankaLoading.value=true

        disposable.add(
            bankaApiServie.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<BankaData>>(){
                    override fun onSuccess(t: List<BankaData>) {
                        println(t)
                        showBankList(t)

                      //  Toast.makeText(getApplication(),"Countries From API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        bankaLoading.value=false
                        bankaError.value=true
                        e.printStackTrace()

                    }

                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }



}
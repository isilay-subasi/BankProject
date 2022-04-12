package com.example.bankaproject.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bankaproject.R
import com.example.bankaproject.databinding.ItemBankaBinding
import com.example.bankaproject.model.BankaData

class BankaAdapter (val bankaList : ArrayList<BankaData>) : RecyclerView.Adapter<BankaAdapter.BankaViewHolder>(){

    class BankaViewHolder(var view : ItemBankaBinding) : RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.item_banka,parent,false)
        val view=DataBindingUtil.inflate<ItemBankaBinding>(inflater, R.layout.item_banka,parent,false)
        return BankaViewHolder(view)
    }


    override fun onBindViewHolder(holder: BankaViewHolder, position: Int) {


            holder.view.bank=bankaList[position]
            holder.view.bankaSube.text=bankaList[position].dc_BANKA_SUBE

    }

    override fun getItemCount(): Int {
        return bankaList.size
    }

    fun updateBankaList(newBankList : List<BankaData>){
        bankaList.clear()
        bankaList.addAll(newBankList)
        notifyDataSetChanged()

    }


}
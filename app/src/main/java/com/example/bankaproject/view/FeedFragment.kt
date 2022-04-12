package com.example.bankaproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankaproject.R
import com.example.bankaproject.adapter.BankaAdapter
import com.example.bankaproject.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    private lateinit var viewModel : FeedViewModel
    private val bankaAdapter = BankaAdapter(arrayListOf())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel=ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        bankaList.layoutManager=LinearLayoutManager(context)
        bankaList.setHasFixedSize(true)
        bankaList.adapter=bankaAdapter

        //Kullanıcı refresh ettiğinde ne olacak ?
        swipeRefreshLayout.setOnRefreshListener {
            bankaList.visibility=View.GONE
            bankaError.visibility=View.GONE
            bankaLoading.visibility=View.VISIBLE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing=false
        }


        observeLiveData()

    }


    private fun observeLiveData(){
        viewModel.datas.observe(viewLifecycleOwner, Observer { datas ->
            datas?.let {

                bankaList.visibility=View.VISIBLE
                bankaAdapter.updateBankaList(datas)

            }

        })

        viewModel.bankaError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it){
                    bankaError.visibility=View.VISIBLE
                    bankaList.visibility=View.GONE
                    bankaLoading.visibility=View.GONE

                }else bankaError.visibility=View.GONE

            }


        })

        viewModel.bankaLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it){
                    bankaLoading.visibility=View.VISIBLE
                    bankaList.visibility=View.GONE
                    bankaError.visibility=View.GONE
                }else{
                    bankaLoading.visibility=View.GONE
                }
            }

        })


    }

}
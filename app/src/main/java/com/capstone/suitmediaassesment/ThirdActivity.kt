package com.capstone.suitmediaassesment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.capstone.suitmediaassesment.databinding.ActivityThirdBinding
import com.capstone.suitmediaassesment.viewmodel.ThirdActivityViewModel

class ThirdActivity : AppCompatActivity(),SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var adapterUser: UserAdapter
    private var isLoading = false
    private lateinit var viewModel: ThirdActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ThirdActivityViewModel::class.java]
        setupRecyclerView()
        getUser(false)
        binding.swipeRefresh.setOnRefreshListener(this)
        }

    private fun  getUser(isOnRefresh: Boolean){
        isLoading = true
        viewModel.getUsers(binding,isOnRefresh,adapterUser,this)
        }



    private fun setupRecyclerView() {
        binding.rvUser.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ThirdActivity)
            adapterUser = UserAdapter()
            adapter = adapterUser

            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibileItemCount = layoutManager?.childCount
                    val pastVisibleItem = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    val total = adapterUser.itemCount
                    if(!isLoading && viewModel.page < viewModel.totalPage){
                        if(visibileItemCount!! + pastVisibleItem >= total){
                            viewModel.page++
                            getUser(false)
                        }

                    }
                }
            })
        }
    }

    override fun onRefresh() {
        adapterUser.clearList()
        viewModel.page = 1
        getUser(true)
        isLoading = false
    }

}
package com.capstone.suitmediaassesment.viewmodel

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.capstone.suitmediaassesment.UserAdapter
import com.capstone.suitmediaassesment.api.RetrofitClient
import com.capstone.suitmediaassesment.databinding.ActivityThirdBinding
import com.capstone.suitmediaassesment.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdActivityViewModel : ViewModel() {
    var page = 1
    var totalPage = 1
    fun getUsers(
        binding: ActivityThirdBinding,
        isOnRefresh: Boolean,
        adapter: UserAdapter,
        context: Context
    ) {
        if (!isOnRefresh) binding.progressBar.visibility = View.VISIBLE
        val parameters = HashMap<String, String>()
        parameters["page"] = page.toString()
        RetrofitClient.apiInstance.getUsers(parameters).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                totalPage = response.body()?.totalPages!!
                val listResponse = response.body()?.data

                if (listResponse != null) {
                    adapter.addList(listResponse)
                }
                binding.swipeRefresh.isRefreshing = false
                binding.progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(context, t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }
}
package com.kgstrivers.employee.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kgstrivers.employee.Model.Employee
import com.kgstrivers.employee.Network.RetroInstance
import com.kgstrivers.employee.Network.Retroservice
import com.kgstrivers.employee.Repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel constructor(private val repository: MainRepository) :ViewModel() {

    var employeelist = MutableLiveData<List<Employee>>()
    var errorMessage = MutableLiveData<String>()

    init {
       employeelist= MutableLiveData()
    }

    fun getRecyclerListObserver() : MutableLiveData<List<Employee>>{
        return employeelist
    }

    fun getAllEmployee() {

        val response = repository.getAllEmployee()
        response.enqueue(object : Callback<List<Employee>> {
            override fun onResponse(call: Call<List<Employee>>, response: Response<List<Employee>>) {
                Log.d("Response",response.body().toString())
                employeelist.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                Log.d("OnFailure",t.toString())
                errorMessage.postValue(t.message)
            }
        })
    }
}
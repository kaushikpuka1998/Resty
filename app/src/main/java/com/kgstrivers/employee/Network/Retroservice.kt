package com.kgstrivers.employee.Network

import com.kgstrivers.employee.Model.Employee
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface Retroservice {

    @GET("employees")
    fun getDataFromApi() : Call<List<Employee>>

    companion object {

        var retrofitService: Retroservice? = null

        fun getInstance() : Retroservice {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.0.103:9292/rest/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(Retroservice::class.java)
            }
            return retrofitService!!
        }
    }
}
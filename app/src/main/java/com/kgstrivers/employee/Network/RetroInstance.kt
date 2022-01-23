package com.kgstrivers.employee.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object{
        val base = "http://192.168.0.103:9292/rest/"

        fun getRetroInstance() : Retrofit {
            return Retrofit.Builder().baseUrl(base).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}
package com.kgstrivers.employee.Repository

import com.kgstrivers.employee.Network.Retroservice
import com.kgstrivers.employee.Network.Retroservice.Companion.retrofitService

class MainRepository constructor(private val retroservice: Retroservice) {

    fun getAllEmployee() = retroservice.getDataFromApi()
}
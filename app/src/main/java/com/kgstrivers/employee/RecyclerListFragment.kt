package com.kgstrivers.employee

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kgstrivers.employee.Adapter.RecyclerviewAdapter

import com.kgstrivers.employee.Network.Retroservice

import com.kgstrivers.employee.Repository.MainRepository

import com.kgstrivers.employee.ViewModel.MainActivityViewModel
import com.kgstrivers.employee.ViewModel.MyViewModelFactory


class RecyclerListFragment : Fragment() {

    private lateinit var recyclerAdapter : RecyclerviewAdapter
    private val retroservice = Retroservice.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  =  inflater.inflate(R.layout.fragment_recycler_list, container, false)


        initViewModel()
        intiViewModel(view)
        return view
    }

    private fun intiViewModel(view: View?) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView?.addItemDecoration(decoration)

        recyclerAdapter = RecyclerviewAdapter()
        recyclerView?.adapter = recyclerAdapter

    }
    private fun initViewModel()
    {
        val viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retroservice))).get(MainActivityViewModel::class.java)

       viewModel.employeelist.observe(viewLifecycleOwner, Observer {
           Log.d(TAG, "onCreate: $it")
           recyclerAdapter.setUpdatedData(it)
       })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {

        })

        viewModel.getAllEmployee()
    }
    companion object {

        @JvmStatic
        fun newInstance() =
            RecyclerListFragment()
    }
}
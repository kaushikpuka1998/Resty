package com.kgstrivers.employee.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kgstrivers.employee.Model.Employee
import com.kgstrivers.employee.R

class RecyclerviewAdapter : RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>() {



    var items = mutableListOf<Employee>()

    fun setUpdatedData(items: List<Employee>)
    {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }
    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val forid = view.findViewById<TextView>(R.id.forid)
        val forname = view.findViewById<TextView>(R.id.forname)
        val foraddress = view.findViewById<TextView>(R.id.foraddress)

        fun bind(data :Employee)
        {
            forid.text = data.id.toString()
            forname.text = data.name
            foraddress.text = data.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_single_elements ,parent,false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }


}
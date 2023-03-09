package com.dsige.cca.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsige.cca.data.model.RegisterWork
import com.dsige.cca.databinding.ItemWorkBinding

class WorkAdapter(
    var dataList: MutableList<RegisterWork>,
    val listener: (data: RegisterWork) -> Unit,
) : RecyclerView.Adapter<WorkAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemWorkBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = dataList.size

    inner class ViewHolder(private val binding: ItemWorkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(item: RegisterWork) = with(binding.root) {

        }
    }
}
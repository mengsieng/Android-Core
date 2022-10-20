package com.msdevelop.base.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<VB: ViewBinding, T>
    : RecyclerView.Adapter<BaseRecyclerViewAdapter<VB, T>.ViewHolder>() {

    abstract fun setLayout(inflater: LayoutInflater, parent: ViewGroup): VB
    abstract fun bindView(context: Context, binding: VB, item: T)
    abstract fun setItem(): ArrayList<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            setLayout(LayoutInflater.from(parent.context), parent),
            parent.context,
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = setItem()[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = setItem().size

    inner class ViewHolder(
        private val binding: VB,
        private val context: Context,
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: T){
            bindView(context, binding, item)
        }
    }

}
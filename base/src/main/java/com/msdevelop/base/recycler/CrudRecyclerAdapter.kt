package com.msdevelop.base.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class CrudRecyclerAdapter<VB: ViewBinding, T>
    : RecyclerView.Adapter<CrudRecyclerAdapter<VB, T>.ViewHolder>(){

    abstract fun layout(inflater: LayoutInflater, parent: ViewGroup): VB
    abstract fun bindView(binding: VB, item: T, position: Int)

    protected lateinit var context: Context
    internal val items = ArrayList<T>()

    fun setItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyItemRangeChanged(0, this.items.size)
    }

    fun clearItem(){
        this.items.clear()
        notifyItemRangeChanged(0, this.items.size)
    }

    fun addItem(item: T){
        this.items.add(item)
        notifyItemInserted(this.items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            layout(LayoutInflater.from(context), parent)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = this.items[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int = this.items.size

    open inner class ViewHolder(
        private val binding: VB,
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: T, position: Int){
            bindView(binding, item, position)
        }
    }

}
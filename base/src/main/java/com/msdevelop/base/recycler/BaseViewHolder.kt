package com.msdevelop.base.recycler

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T>(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(item: T, position: Int)
}
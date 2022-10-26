package com.msdevelop.base.recycler.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.ig.base.databinding.LoadingBinding
import com.msdevelop.base.recycler.BaseViewHolder
import java.util.*
import java.util.logging.Handler
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

abstract class PagingRecyclerAdapter <VB: ViewBinding, T>(
    private val pagingListener: PageListener,
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    abstract fun layout(inflater: LayoutInflater, parent: ViewGroup): VB
    abstract fun bindView(binding: VB, item: T, position: Int)
    abstract fun recyclerView(): RecyclerView
    abstract fun pageSize(): Int

    private val itemList = 0
    private val loading = 1

    private var isLastPage: Boolean = false
    private var page = 0

    private var isLoading = false

    /**
     * Public variable
    * */
    protected lateinit var context: Context

    private val items = ArrayList<T?>()
    fun setItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        refresh()
        notifyItemRangeChanged(0, this.items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return when(viewType){
            loading -> LoadingHolder(
                LoadingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
            )
            else -> ItemListHolder(
                layout(LayoutInflater.from(parent.context), parent)
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        if(holder.itemViewType == itemList){
            holder.onBind(items[position]!!, position)
        }
    }

    override fun getItemCount(): Int = this.items.size

    override fun getItemViewType(position: Int): Int {
        return if (items[position] != null) itemList else loading
    }

    fun loadMoreItems(items: List<T>){
        hideLoading()
        this.items.addAll(items)
        notifyItemInserted(items.size)
        if(items.size < pageSize()){
            isLastPage = true
        }
        page += 1
    }

    private fun showLoading(){
        if(!isLastPage){
            this.items.add(null)
            isLoading = true
            notifyItemInserted(this.items.size - 1)
        }
    }

    private fun hideLoading(){
        this.items.removeAt(this.items.size - 1)
        notifyDataSetChanged()
        isLoading = false
    }

    private fun refresh(){
        isLastPage = false
        page = 1
    }

    fun build(){
        /// Get Recycle from activity
        recyclerView().adapter = this
        recyclerView().addOnScrollListener(
            object: PagingScrollListener(recyclerView().layoutManager as LinearLayoutManager){
                override fun loadMore(){
                    if(!isLoading){
                        showLoading()
                        pagingListener.loadMore(pageSize(), page)
                    }
                }
                override fun isLastPage(): Boolean = isLastPage
                override fun itemSize(): Int = itemCount
            }
        )
    }

    inner class LoadingHolder(binding: ViewBinding): BaseViewHolder<T>(binding){
        override fun onBind(item: T, position: Int) {
        }
    }

    inner class ItemListHolder(private val binding: VB): BaseViewHolder<T>(binding){
        override fun onBind(item: T, position: Int) {
            bindView(binding, item, position)
        }
    }

}
package com.msdevelop.base.recycler.paging

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.msdevelop.base.recycler.CrudRecyclerAdapter

abstract class PagingRecyclerAdapter <VB: ViewBinding, T>(
    private val pagingListener: PageListener,
) : CrudRecyclerAdapter<VB,T>() {

    abstract fun recyclerView(): RecyclerView
    abstract fun pageSize(): Int
    private var isLastPage: Boolean = false

    fun loadMoreItems(items: ArrayList<T>){
        this.items.addAll(items)
        notifyItemInserted(this.items.size)
        if(items.size < pageSize()){
            isLastPage = true
        }
    }

    fun build(){
        /// Get Recycle from activity
        recyclerView().adapter = this
        recyclerView().addOnScrollListener(
            object: PagingScrollListener(recyclerView().layoutManager as LinearLayoutManager){
                override fun loadMore() = pagingListener.loadMore(pageSize())
                override fun isLastPage(): Boolean = isLastPage
                override fun itemSize(): Int = itemCount
            }
        )
    }

}
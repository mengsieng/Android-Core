package com.msdevelop.base.recycler.paging

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PagingScrollListener(
    private val layoutManager: LinearLayoutManager,
) : RecyclerView.OnScrollListener(){

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val lastPosition: Int = layoutManager.findLastCompletelyVisibleItemPosition()
        if(!isLastPage()){
            if (dy > 0 && lastPosition == (itemSize() - 1)){
                loadMore()
            }
        }
    }

    protected abstract fun loadMore()
    abstract fun isLastPage(): Boolean
    abstract fun itemSize(): Int

}
package com.msdevelop.base.recycler.paging

interface PageListener {
    fun loadMore(pageSize: Int, page: Int)
}
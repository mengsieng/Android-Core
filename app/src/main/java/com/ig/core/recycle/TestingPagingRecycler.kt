package com.ig.core.recycle

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ig.core.databinding.ItemCardBinding
import com.msdevelop.base.recycler.paging.PageListener
import com.msdevelop.base.recycler.paging.PagingRecyclerAdapter

class TestingPagingRecycler(
    private val recyclerView: RecyclerView,
    pagingListener: PageListener,
): PagingRecyclerAdapter<ItemCardBinding, String>(pagingListener) {

    override fun layout(inflater: LayoutInflater, parent: ViewGroup): ItemCardBinding
        = ItemCardBinding.inflate(inflater, parent, false)

    override fun bindView(context: Context, binding: ItemCardBinding, item: String) {
        binding.txtTitle.text = item
    }

    override fun recyclerView(): RecyclerView = recyclerView
    override fun pageSize(): Int = 10

}
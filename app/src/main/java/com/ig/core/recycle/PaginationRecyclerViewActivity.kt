package com.ig.core.recycle

import com.ig.core.databinding.ActivityPaginationRecyclerViewBinding
import com.msdevelop.base.activities.BaseActivity
import com.msdevelop.base.recycler.paging.PageListener

class PaginationRecyclerViewActivity : BaseActivity<ActivityPaginationRecyclerViewBinding>() {

    private val items1 = arrayListOf("Cat", "Dog", "Lion", "Tiger", "Egle", "Mouse", "Shrimp")
    private val items2 = arrayListOf("Apple", "Banana", "Orange", "Peal")

    private lateinit var adapter: TestingPagingRecycler

    override fun setViewBinding(): ActivityPaginationRecyclerViewBinding
        = ActivityPaginationRecyclerViewBinding.inflate(layoutInflater)

    override fun onCreateView() {
        onSetupRecyclerView()
        adapter.setItems(items1)
    }

    private fun onSetupRecyclerView() {
        adapter = TestingPagingRecycler(binding.rvItemTesting, object : PageListener {
            override fun loadMore(pageSize: Int) {
                adapter.loadMoreItems(items2)
            }
        })
        adapter.build()
    }

}
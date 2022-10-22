package com.ig.core.recycle

import android.util.Log
import com.ig.core.databinding.ActivityPaginationRecyclerViewBinding
import com.msdevelop.base.activities.BaseActivity
import com.msdevelop.base.recycler.paging.PageListener

class PaginationRecyclerViewActivity : BaseActivity<ActivityPaginationRecyclerViewBinding>() {

    private val items1 = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    private val items2 = arrayListOf("11", "12", "13", "14", "15", "16", "17", "18", "19", "20")
    private val items3 = arrayListOf("21", "22", "23", "24", "25", "26", "27", "28", "29", "30")
    private val items4 = arrayListOf("31", "32", "33")

    private lateinit var adapter: TestingPagingRecycler

    override fun setViewBinding(): ActivityPaginationRecyclerViewBinding
        = ActivityPaginationRecyclerViewBinding.inflate(layoutInflater)

    override fun onCreateView() {
        onSetupRecyclerView()
        adapter.setItems(items1)
    }

    private fun onSetupRecyclerView() {
        adapter = TestingPagingRecycler(binding.rvItemTesting, object : PageListener {
            override fun loadMore(pageSize: Int, page: Int) {
                when (page) {
                    1 -> {
                        adapter.loadMoreItems(items2)
                    }
                    2 -> {
                        adapter.loadMoreItems(items3)
                    }
                    else -> {
                        adapter.loadMoreItems(items4)
                    }
                }
            }
        })
        adapter.build()
    }

}
package com.example.lex.hhmini.presentation.base

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import javax.inject.Inject

interface OnLoadMoreListener {
    fun onLoadMore(page: Int)
}

class EndlessRecyclerOnScrollListener @Inject constructor(private val mLinearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var loading = false
    private var isEnabled = true
    private var currentPage = 1
    var onLoadMoreListener: OnLoadMoreListener? = null

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount = mLinearLayoutManager.itemCount
        val lastItem = mLinearLayoutManager.findLastVisibleItemPosition()

        if (!loading) {
            if (lastItem + THRESHOLD > totalItemCount) {
                if (isEnabled) {
                    loading = true
                    onLoadMoreListener?.onLoadMore(currentPage + 1)
                }
            }
        }
    }

    fun reset() {
        loading = false
        currentPage = 1
        enableLoadMore()
    }

    /**
     * Отключает проверку на необходимость подгрузки новых страниц
     */
    fun disableLoadMore() {
        isEnabled = false
    }

    /**
     * Включает проверку на необходимость подгрузки новых страниц
     */
    fun enableLoadMore() {
        isEnabled = true
    }

    fun setCurrentPage(currentPage: Int) {
        this.currentPage = currentPage
    }

    fun loadingFinished() {
        loading = false
    }

    companion object {
        // The minimum amount of items to have below your current scroll position before loading more.
        private val THRESHOLD = 5
    }
}

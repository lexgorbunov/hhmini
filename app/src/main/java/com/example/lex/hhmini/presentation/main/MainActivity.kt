package com.example.lex.hhmini.presentation.main

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.lex.hhmini.R
import com.example.lex.hhmini.data.models.Vacancy
import com.example.lex.hhmini.di.main.DaggerMainViewComponent
import com.example.lex.hhmini.di.main.MainViewModule
import com.example.lex.hhmini.presentation.base.EndlessRecyclerOnScrollListener
import com.example.lex.hhmini.presentation.base.OnLoadMoreListener
import com.example.lex.hhmini.utils.extensions.getApp
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView, SwipeRefreshLayout.OnRefreshListener,
        OnLoadMoreListener {

    @Inject
    lateinit var presenter: MainViewPresenter
    @Inject
    lateinit var layoutManager: LinearLayoutManager
    @Inject
    lateinit var adapter: VacanciesAdapter
    @Inject
    lateinit var scrollListener: EndlessRecyclerOnScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerMainViewComponent.builder()
                .appComponent(getApp().appComponent)
                .mainViewModule(MainViewModule(this, this))
                .build().inject(this)

        setContentView(R.layout.activity_main)

        list.layoutManager = layoutManager
        list.adapter = adapter
        list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        scrollListener.onLoadMoreListener = this
        list.addOnScrollListener(scrollListener)

        swipe_refresh_layout?.post { swipe_refresh_layout.isRefreshing = true }
        swipe_refresh_layout?.setOnRefreshListener(this)

        presenter.loadVacancies(1)
    }

    override fun displayVacancies(list: List<Vacancy>, page: Int) {
        swipe_refresh_layout?.post { swipe_refresh_layout.isRefreshing = false }

        if (page == 1) {
            adapter.setData(list)
            scrollListener.reset()
        } else {
            adapter.addData(list)
        }
        scrollListener.setCurrentPage(page)
        if (list.isEmpty()) scrollListener.disableLoadMore()
        scrollListener.loadingFinished()
    }

    override fun onRefresh() {
        scrollListener.reset()
        presenter.loadVacancies(1)
    }

    override fun onLoadMore(page: Int) {
        swipe_refresh_layout.isRefreshing = true
        presenter.loadVacancies(page)
    }
}

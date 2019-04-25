package com.netguru.testmovies.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.netguru.testmovies.common.ViewState
import com.netguru.testmovies.common.switchMap
import com.netguru.testmovies.data.Movie
import com.netguru.testmovies.repo.NetworkRepo
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(repo: NetworkRepo): ViewModel() {

    private val disposable = CompositeDisposable()
    private val dataSourceFactory = MovieListDataSourceFactory(repo, disposable)

    val pagedListData: LiveData<PagedList<Movie>> by lazy {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        LivePagedListBuilder<Int, Movie>(dataSourceFactory, config).build()
    }


    fun getState(): LiveData<ViewState> {
        return dataSourceFactory.createdDataSource.switchMap {
            it.state
        }
    }
    override fun onCleared() {
        disposable.clear()
    }

    fun setYear(newVal: Int?) {
        dataSourceFactory.setYear(newVal)
        pagedListData.value?.dataSource?.invalidate()
    }

    companion object {
        const val PAGE_SIZE = 2
    }
}



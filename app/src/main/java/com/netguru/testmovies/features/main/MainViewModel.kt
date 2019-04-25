package com.netguru.testmovies.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.netguru.testmovies.data.Movie
import com.netguru.testmovies.repo.NetworkRepo
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(repo: NetworkRepo): ViewModel() {

    private val disposable = CompositeDisposable()

    val pagedListData: LiveData<PagedList<Movie>> by lazy {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        LivePagedListBuilder<Int, Movie>(MovieListDataSource.createFactory(repo, disposable), config).build()
    }

    override fun onCleared() {
        disposable.clear()
    }

    companion object {
        const val PAGE_SIZE = 2
    }
}
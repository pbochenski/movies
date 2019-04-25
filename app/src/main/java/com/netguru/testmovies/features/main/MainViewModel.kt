package com.netguru.testmovies.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.netguru.testmovies.data.Movie
import io.reactivex.disposables.CompositeDisposable

class MainViewModel: ViewModel() {

    val pagedListData: LiveData<PagedList<Movie>> by lazy {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        LivePagedListBuilder<Int, Movie>(MovieListDataSource.createFactory(), config).build()
    }

    companion object {
        const val PAGE_SIZE = 2
    }
}
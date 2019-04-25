package com.netguru.testmovies.features.main

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.netguru.testmovies.common.ViewState
import com.netguru.testmovies.data.Movie
import com.netguru.testmovies.repo.NetworkRepo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class MovieListDataSource(
    private val repo: NetworkRepo,
    private val disposable: CompositeDisposable,
    private val year: Int?
): PageKeyedDataSource<Int, Movie>() {

    val state = MutableLiveData<ViewState>()


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        getPage(INIT_PAGE) {
            callback.onResult(it, null, INIT_PAGE + 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val after = if (params.key > MAX_PAGES) { null } else { params.key + 1 }
        getPage(params.key) { callback.onResult(it, after) }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val before = if (params.key == INIT_PAGE) { null } else { params.key - 1 }
        getPage(params.key) { callback.onResult(it, before) }
    }

    private fun getPage(page: Int, callback: (List<Movie>) -> Unit) {
        state.postValue(ViewState.LOADING)
            repo.getMovieDiscover(page, year)
                .subscribeBy(
                    onSuccess = {
                        if(it.movies.isNullOrEmpty()){
                            state.postValue(ViewState.EMPTY)
                        } else {
                            state.postValue(ViewState.DONE)
                            callback(it.movies)
                        }
                    },
                    onError = {
                        state.postValue(ViewState.ERROR(it.message))
                        Timber.d("getMovieDiscover returned error")
                    })
                .addTo(disposable)
    }

    companion object {
        const val INIT_PAGE = 1
        private const val MAX_PAGES = 1000

    }
}
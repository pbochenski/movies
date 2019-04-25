package com.netguru.testmovies.features.main

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.netguru.testmovies.data.Movie
import com.netguru.testmovies.repo.NetworkRepo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class MovieListDataSource(private val repo: NetworkRepo,
                          private val disposable: CompositeDisposable): PageKeyedDataSource<Int, Movie>() {


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
        repo.getMovieDiscover(page)
            .subscribeBy(
                onSuccess = {
                    callback(it.movies ?: emptyList())
                },
                onError = {
                    Timber.d("getMovieDiscover returned error")
                })
            .addTo(disposable)
    }

    companion object {
        const val INIT_PAGE = 1
        private const val MAX_PAGES = 1000
        fun createFactory(repo: NetworkRepo, disposable: CompositeDisposable): DataSource.Factory<Int,Movie> {
            return object : DataSource.Factory<Int,Movie>(){
                override fun create(): DataSource<Int, Movie> {
                    return MovieListDataSource(repo, disposable)
                }
            }
        }
    }
}
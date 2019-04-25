package com.netguru.testmovies.repo

import com.netguru.testmovies.BuildConfig
import com.netguru.testmovies.data.Movie
import com.netguru.testmovies.data.MovieDiscoveryResponse
import com.netguru.testmovies.remote.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NetworkRepo(private val api: ApiService) {

    private fun <T> Single<T>.withDefaultThreads(): Single<T> = this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getMovieDetailsById(movieId: Int): Single<Movie> =
        api.getMovieDetailsById(API_VERSION, movieId, BuildConfig.API_KEY)
            .withDefaultThreads()

    fun getMovieDiscover(page: Int): Single<MovieDiscoveryResponse> =
        api.getMovieDiscover(API_VERSION, BuildConfig.API_KEY, page)
            .withDefaultThreads()

    companion object {
        private const val API_VERSION = 3
    }
}